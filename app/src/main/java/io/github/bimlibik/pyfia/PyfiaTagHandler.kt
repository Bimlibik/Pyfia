package io.github.bimlibik.pyfia

import android.graphics.Color
import android.text.*
import android.text.style.BackgroundColorSpan
import android.text.style.BulletSpan
import android.text.style.LeadingMarginSpan
import org.xml.sax.XMLReader
import java.util.*


/**
 * Some parts of this code are based on
 * https://github.com/SufficientlySecure/html-textview/blob/master/HtmlTextView/src/main/java/org/sufficientlysecure/htmltextview/HtmlTagHandler.java
 */
class PyfiaTagHandler : Html.TagHandler {

    companion object {
        // Custom tags
        private const val CODE = "code"
        private const val UNORDERED_LIST = "ul-tag"
        private const val ORDERED_LIST = "ol-tag"
        private const val LIST_ITEM = "li-tag"

        private const val CODE_COLOR = "#8FC1BDBD"
        private const val DEFAULT_INDENT = 20
    }

    private object Ul
    private object Ol
    private object Code

    /**
     * Keeps track of lists (ol, ul). On bottom of Stack is the outermost list
     * and on top of Stack is the most nested list
     */
    private val listParents = Stack<String>()

    override fun handleTag(
        opening: Boolean,
        tag: String?,
        output: Editable?,
        xmlReader: XMLReader?
    ) {
        if (output == null) return

        when (tag) {
            CODE -> handleCodeTag(opening, output)
            UNORDERED_LIST, ORDERED_LIST -> handleListTag(opening, tag)
            LIST_ITEM -> handleListItemTag(opening, output)
        }
    }

    private fun handleListItemTag(opening: Boolean, output: Editable) {
        if (output.isNotEmpty() && output[output.length - 1] != '\n') {
            output.append("\n")
        }

        if (opening) {
            when (listParents.lastElement()) {
                ORDERED_LIST -> {
                    start(output as SpannableStringBuilder, Ol)
                }
                UNORDERED_LIST -> {
                    start(output as SpannableStringBuilder, Ul)
                }
            }
        } else {
            // closing tag
            val indent = listParents.size * DEFAULT_INDENT

            when (listParents.lastElement()) {
                ORDERED_LIST -> {
                    end(
                        output as SpannableStringBuilder,
                        Ol::class.java,
                        LeadingMarginSpan.Standard(indent)
                    )
                }
                UNORDERED_LIST -> {
                    end(
                        output as SpannableStringBuilder,
                        Ul::class.java,
                        LeadingMarginSpan.Standard(indent),
                        BulletSpan(DEFAULT_INDENT)
                    )
                }
            }
        }
    }

    private fun handleListTag(opening: Boolean, tag: String?) {
        if (tag == null) return

        if (opening) {
            listParents.push(tag)
        } else {
            listParents.pop()
        }
    }

    private fun handleCodeTag(opening: Boolean, output: Editable) {
        if (opening) {
            start(
                output as SpannableStringBuilder,
                Code
            )
        } else {
            end(
                output as SpannableStringBuilder,
                Code::class.java,
                BackgroundColorSpan(Color.parseColor(CODE_COLOR))
            )
        }
    }

    /**
     * Get last marked position of a specific tag kind (private objects)
     */
    private fun <T> getLast(text: Spanned, kind: Class<T>): Any? {
        val objs: Array<out T> = text.getSpans(0, text.length, kind)
        return if (objs.isEmpty()) {
            null
        } else {
            for (i in objs.size downTo 1) {
                if (text.getSpanFlags(objs[i - 1]) == Spannable.SPAN_MARK_MARK) {
                    return objs[i - 1]
                }
            }
            return null
        }
    }

    /**
     * Mark the opening tag by using private objects
     */
    private fun start(text: SpannableStringBuilder, mark: Any) {
        val len = text.length
        text.setSpan(mark, len, len, Spannable.SPAN_MARK_MARK)
    }

    /**
     * Modified from android.text.Html
     */
    private fun <T> end(
        text: SpannableStringBuilder, kind: Class<T>,
        vararg replaces: Any
    ) {
        val len = text.length
        val obj = getLast(text, kind)
        val where = text.getSpanStart(obj)

        text.removeSpan(obj)

        if (where != len) {
            replaces.forEach { replace ->
                text.setSpan(replace, where, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
    }

}
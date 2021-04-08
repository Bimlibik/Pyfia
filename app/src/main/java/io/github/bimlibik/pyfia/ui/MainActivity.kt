package io.github.bimlibik.pyfia.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.bimlibik.pyfia.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

//        val tvMain = findViewById<TextView>(R.id.tv_text)
        val htmlList = "<p>Список:</p>" +
                "<ul-tag>" +
                    "<li-tag>Item 1 </li-tag>" +
                    "<li-tag>Item 2 </li-tag>" +
                "</ul-tag><br>" +
                "Some text"
        val htmlOlList = "List:<br>" +
                "<ol-tag>" +
                    "<li-tag>1. Item 1 </li-tag>" +
                    "<ul-tag>" +
                        "<li-tag>Subitem 1</li-tag>" +
                        "<li-tag>Subitem 2</li-tag>" +
                    "</ul-tag>" +
                    "<li-tag>2. Item 2 </li-tag>" +
                    "<ul-tag>" +
                        "<li-tag>Subitem 1</li-tag>" +
                        "<li-tag>Subitem 2</li-tag>" +
                    "</ul-tag>" +
                "</ol-tag><br>" +
                "Some text"

        val htmlCode = "<p>Класс <code>Application</code> имеет метод <code>registerActivityLifecycleCallbacks()</code>, " +
                "который принимает параметром интерфейс <code>ActivityLifecycleCallbacks</code>. " +
                "Этот метод позволяет зарегистрировать коллбэк, который сообщает о вызове методов жизненного цикла всех активити в приложении.</p>"
//        tvMain.setText(HtmlCompat.fromHtml(htmlOlList, HtmlCompat.FROM_HTML_MODE_COMPACT, null,  PyfiaTagHandler()))
    }
}
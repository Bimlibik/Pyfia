package io.github.bimlibik.pyfia.ui.topics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.bimlibik.pyfia.databinding.ItemTopicBinding

class TopicsAdapter(private val callback: TopicsFragment.TopicCallback)
    : ListAdapter<String, TopicsAdapter.TopicsViewHolder>(TopicsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsViewHolder {
        val binding = ItemTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopicsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicsViewHolder, position: Int) {
        holder.bind(position)
    }

    /**
     * ViewHolder
     */
    inner class TopicsViewHolder(private val binding: ItemTopicBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = getItem(position)
            binding.title = "${position + 1}. $item"

            itemView.setOnClickListener {
                callback.onTopicClick(item)
            }
        }
    }

}

class TopicsDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}
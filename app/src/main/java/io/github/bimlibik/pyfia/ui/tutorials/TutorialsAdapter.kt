package io.github.bimlibik.pyfia.ui.tutorials

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.bimlibik.pyfia.databinding.ItemTutorialBinding

class TutorialsAdapter(private val callback: TutorialsFragment.TutorialCallback)
    : ListAdapter<String, TutorialsAdapter.TutorialsViewHolder>(TutorialsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialsViewHolder {
        val binding = ItemTutorialBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TutorialsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TutorialsViewHolder, position: Int) {
        holder.bind(position)
    }

    /**
     * ViewHolder
     */
    inner class TutorialsViewHolder(private val binding: ItemTutorialBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = getItem(position)
            binding.title = item
            itemView.setOnClickListener {
                callback.onTutorialClick(item)
            }
        }
    }

}

class TutorialsDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}
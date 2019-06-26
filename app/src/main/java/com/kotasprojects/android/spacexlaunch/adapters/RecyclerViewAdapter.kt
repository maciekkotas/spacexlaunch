package com.kotasprojects.android.spacexlaunch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kotasprojects.android.spacexlaunch.databinding.ListViewItemBinding
import com.kotasprojects.android.spacexlaunch.network.SpaceXLunchProperty

class RecyclerViewAdapter(private val onClickListenert: OnClickListener) :
    ListAdapter<SpaceXLunchProperty, RecyclerViewAdapter.SpaceXPropertyViewHolder>(
        DiffCallback
    ) {


    class SpaceXPropertyViewHolder(private var binding: ListViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(spaceXLunchProperty: SpaceXLunchProperty) {
            binding.property = spaceXLunchProperty
            binding.executePendingBindings()

        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<SpaceXLunchProperty>() {
        override fun areItemsTheSame(oldItem: SpaceXLunchProperty, newItem: SpaceXLunchProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SpaceXLunchProperty, newItem: SpaceXLunchProperty): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpaceXPropertyViewHolder {
        return SpaceXPropertyViewHolder(
            ListViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: SpaceXPropertyViewHolder, position: Int) {
        val spaceXProperty = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListenert.onClick(spaceXProperty)
        }
        holder.bind(spaceXProperty)
    }

    class OnClickListener(val clickListener: (spaceXLunchProperty: SpaceXLunchProperty) -> Unit) {
        fun onClick(spaceXLunchProperty: SpaceXLunchProperty) = clickListener(spaceXLunchProperty)
    }

}

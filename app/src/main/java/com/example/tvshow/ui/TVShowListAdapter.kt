package com.example.tvshow.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshow.databinding.ListViewItemBinding
import com.example.tvshow.network.TVShow

class TVShowListAdapter(val clickListener: TVShowListener)  :
    ListAdapter<TVShow, TVShowListAdapter.TVShowListViewHolder> (DiffCallback){
    class TVShowListViewHolder(
        var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: TVShowListener, tvShow: TVShow) {
            binding.tvshow = tvShow
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<TVShow>() {

        override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TVShowListViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TVShowListViewHolder, position: Int) {
        val tvShow = getItem(position)
        holder.bind(clickListener ,tvShow)
    }
}
class TVShowListener(val clickListener: (tvShow: TVShow) -> Unit) {
    fun onClick(tvShow: TVShow) = clickListener(tvShow)
}
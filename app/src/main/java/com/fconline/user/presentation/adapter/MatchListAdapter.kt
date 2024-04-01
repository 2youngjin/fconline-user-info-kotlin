package com.fconline.user.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fconline.user.databinding.ItemMatchlistBinding
import com.fconline.user.domain.model.MatchId

class MatchListAdapter :
    ListAdapter<MatchId, MatchListAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<MatchId>() {
        override fun areItemsTheSame(oldItem: MatchId, newItem: MatchId): Boolean {
            return oldItem.matchId == newItem.matchId
        }

        override fun areContentsTheSame(oldItem: MatchId, newItem: MatchId): Boolean {
            return oldItem == newItem
        }
    }) {

    inner class ViewHolder(private val binding: ItemMatchlistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MatchId) {
            binding.matchIdTextView.text = item.matchId
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemMatchlistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
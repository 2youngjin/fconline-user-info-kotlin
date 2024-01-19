package com.fconline.user.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fconline.user.R
import com.fconline.user.databinding.ItemMaxdivisionBinding
import com.fconline.user.domain.model.MaxDivision

class MaxDivisionAdapter : ListAdapter<MaxDivision, MaxDivisionAdapter.ViewHolder>(object :
    DiffUtil.ItemCallback<MaxDivision>() {
    override fun areItemsTheSame(oldItem: MaxDivision, newItem: MaxDivision): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: MaxDivision, newItem: MaxDivision): Boolean {
        return oldItem == newItem
    }
}) {
    inner class ViewHolder(private val binding: ItemMaxdivisionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MaxDivision) {
            binding.matchTypeTextView.text = when (item.matchType) {
                30 -> "리그 친선"
                40 -> "클래식 1on1"
                50 -> "공식경기"
                52 -> "감독모드"
                60 -> "공식친선"
                204 -> "볼타 친선"
                214 -> "볼타 공식"
                224 -> "볼타 AI대전"
                234 -> "볼타 커스텀"
                else -> "없음"
            }
            binding.divisionTextView.text = when (item.division) {
                800 -> "슈퍼 챔피언스"
                900 -> "챔피언스"
                1000 -> "슈퍼 챌린저"
                1100 -> "챌린저 1부"
                1200 -> "챌린저 2부"
                1300 -> "챌린저 3부"
                2000 -> "월드클래스 1부"
                2100 -> "월드클래스 2부"
                2200 -> "월드클래스 3부"
                2300 -> "프로 1부"
                2400 -> "프로 2부"
                2500 -> "프로 3부"
                2600 -> "세미프로 1부"
                2700 -> "세미프로 2부"
                2800 -> "세미프로 3부"
                2900 -> "유망주 1부"
                3000 -> "유망주 2부"
                3100 -> "유망주 3부"
                else -> "없음"
            }
            val image = when (item.division) {
                800 -> R.drawable.ico_rank0
                900 -> R.drawable.ico_rank1
                1000 -> R.drawable.ico_rank2
                1100 -> R.drawable.ico_rank3
                1200 -> R.drawable.ico_rank4
                1300 -> R.drawable.ico_rank5
                2000 -> R.drawable.ico_rank6
                2100 -> R.drawable.ico_rank7
                2200 -> R.drawable.ico_rank8
                2300 -> R.drawable.ico_rank9
                2400 -> R.drawable.ico_rank10
                2500 -> R.drawable.ico_rank11
                2600 -> R.drawable.ico_rank12
                2700 -> R.drawable.ico_rank13
                2800 -> R.drawable.ico_rank14
                2900 -> R.drawable.ico_rank15
                3000 -> R.drawable.ico_rank16
                3100 -> R.drawable.ico_rank17
                else -> R.drawable.baseline_question_mark_24
            }
            binding.rankImageView.load(image) {
                placeholder(R.drawable.baseline_question_mark_24)
                error(R.drawable.baseline_question_mark_24)
            }
            binding.divisionDateTextView.text = item.achievementDate.replace("T", " ")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMaxdivisionBinding.inflate(
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

package com.fconline.user.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fconline.user.R
import com.fconline.user.databinding.FragmentRankInfoBinding
import com.fconline.user.presentation.viewmodel.RankInfoViewModel

class RankInfoFragment : Fragment(R.layout.fragment_rank_info) {

    private lateinit var binding: FragmentRankInfoBinding

    private val viewModel: RankInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRankInfoBinding.inflate(inflater, container, false)

        return binding.root
    }

}
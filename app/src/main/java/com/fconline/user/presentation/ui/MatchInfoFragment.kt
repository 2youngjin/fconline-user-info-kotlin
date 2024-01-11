package com.fconline.user.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fconline.user.R
import com.fconline.user.databinding.FragmentMatchInfoBinding
import com.fconline.user.presentation.viewmodel.MatchInfoViewModel

class MatchInfoFragment : Fragment(R.layout.fragment_match_info) {

    private lateinit var binding: FragmentMatchInfoBinding

    private val viewModel: MatchInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMatchInfoBinding.inflate(inflater, container, false)

        return binding.root
    }

}
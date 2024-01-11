package com.fconline.user.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fconline.user.R
import com.fconline.user.databinding.FragmentMetadataInfoBinding
import com.fconline.user.presentation.viewmodel.MetadataInfoViewModel

class MetadataInfoFragment : Fragment(R.layout.fragment_metadata_info) {

    private lateinit var binding: FragmentMetadataInfoBinding

    private val viewModel: MetadataInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMetadataInfoBinding.inflate(inflater, container, false)

        return binding.root
    }
}
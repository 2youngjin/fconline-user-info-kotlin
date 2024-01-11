package com.fconline.user.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fconline.user.R
import com.fconline.user.databinding.FragmentImageInfoBinding
import com.fconline.user.presentation.viewmodel.ImageInfoViewModel

class ImageInfoFragment : Fragment(R.layout.fragment_image_info) {

    private lateinit var binding: FragmentImageInfoBinding

    private val viewModel: ImageInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentImageInfoBinding.inflate(inflater, container, false)

        return binding.root
    }

}
package com.fconline.user.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fconline.user.R
import com.fconline.user.databinding.FragmentAccountInfoBinding
import com.fconline.user.presentation.viewmodel.AccountInfoViewModel

class AccountInfoFragment : Fragment(R.layout.fragment_account_info) {

    private lateinit var binding: FragmentAccountInfoBinding

    private val viewModel: AccountInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAccountInfoBinding.inflate(inflater, container, false)

        return binding.root
    }

}
package com.fconline.user.presentation.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fconline.user.R
import com.fconline.user.databinding.FragmentAccountInfoBinding
import com.fconline.user.presentation.viewmodel.AccountInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountInfoFragment : Fragment() {

    private lateinit var binding: FragmentAccountInfoBinding
    private val viewModel: AccountInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_account_info,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = this.viewModel

        observeViewModel()
        userNameDoneEvent()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        viewModel.hideKeyboardEvent.observe(viewLifecycleOwner) { shouldHideKeyboard ->
            if (shouldHideKeyboard == true) {
                hideKeyboard()
                viewModel.hideKeyboardEvent.value = false
            }
        }
        viewModel.userInfoVisible.observe(viewLifecycleOwner) {
            binding.userInfoLayer.isVisible = it
        }
    }

    private fun userNameDoneEvent() {
        binding.userNameEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.searchUserId(binding.userNameEditText.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }
    }

    private fun hideKeyboard() {
        val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}
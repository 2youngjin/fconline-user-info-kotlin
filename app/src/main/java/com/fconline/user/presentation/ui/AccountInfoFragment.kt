package com.fconline.user.presentation.ui

import android.annotation.SuppressLint
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.fconline.user.R
import com.fconline.user.databinding.FragmentAccountInfoBinding
import com.fconline.user.presentation.adapter.MatchListAdapter
import com.fconline.user.presentation.adapter.MaxDivisionAdapter
import com.fconline.user.presentation.viewmodel.AccountInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AccountInfoFragment : Fragment() {

    private lateinit var binding: FragmentAccountInfoBinding
    private val viewModel: AccountInfoViewModel by viewModels()
    private val maxDivisionAdapter by lazy { MaxDivisionAdapter() }
    private val matchListAdapter by lazy { MatchListAdapter() }

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        collectViewModel()
        userNameDoneEvent()
    }

    private fun initRecyclerView() {
        binding.maxDivisionRecyclerView.adapter = maxDivisionAdapter
        binding.matchListRecyclerView.adapter = matchListAdapter
    }

    @SuppressLint("SetTextI18n")
    private fun collectViewModel() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.toastMessage.collect { message ->
                message?.let {
                    showToast(it)
                    viewModel.clearToast()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.hideKeyboardEvent.collect { shouldHideKeyboard ->
                if (shouldHideKeyboard == true) {
                    hideKeyboard()
                    viewModel.hideKeyboardEvent.value = false
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userInfoVisible.collect {
                if (it != null) {
                    binding.userInfoLayer.isVisible = it
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.maxDivision.collect {
                maxDivisionAdapter.submitList(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.matchId.collect {
                matchListAdapter.submitList(it)
            }
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

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard() {
        val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}
package com.fconline.user.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fconline.user.data.model.UserId
import com.fconline.user.domain.usecase.GetUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AccountInfoViewModel @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase
) : ViewModel() {

    private val _userId = MutableLiveData<UserId>()
    val userId: LiveData<UserId> get() = _userId

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> get() = _id

    fun searchUserId(nickName: String) {
        Log.e("AccountInfoViewModel", nickName)
        viewModelScope.launch {
            try {
                _userId.value = getUserIdUseCase.getUserId(nickName)
                _id.value = _userId.value?.id ?: ""
                Log.e("AccountInfoViewModel", "id : ${_id.value}")
            } catch (e: HttpException) {
                Log.e(
                    "API_ERROR",
                    "HTTP Error: ${e.code()}, Response: ${e.response()?.errorBody()?.string()}"
                )
            } catch (e: Exception) {
                Log.e("API_ERROR", "Unexpected Error: ${e.message}")
            }
        }
    }
}
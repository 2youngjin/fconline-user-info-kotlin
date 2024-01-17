package com.fconline.user.presentation.viewmodel

import android.content.Context
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fconline.user.data.model.dto.UserIdDto
import com.fconline.user.data.model.dto.UserInfoDto
import com.fconline.user.domain.model.UserInfo
import com.fconline.user.domain.usecase.GetUserIdUseCase
import com.fconline.user.domain.usecase.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AccountInfoViewModel @Inject constructor(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    private val _userInfoVisible = MutableLiveData<Boolean>()
    val userInfoVisible: LiveData<Boolean> = _userInfoVisible

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _userIdDto = MutableLiveData<UserIdDto>()

    private val _userInfoDto = MutableLiveData<UserInfoDto>()

    private val _userNickName = MutableLiveData<String>()
    val userNickName: LiveData<String> = _userNickName

    private val _userLevel = MutableLiveData<String>()
    val userLevel: LiveData<String> = _userLevel

    val hideKeyboardEvent = MutableLiveData<Boolean>()

    fun searchUserId(nickName: String) {

        requestHideKeyboard()

        if (nickName.isEmpty()) {
            _errorMessage.value = "구단주 명을 입력해 주세요."
            return
        }

        viewModelScope.launch {
            try {
                _userIdDto.value = getUserIdUseCase.getUserId(nickName)
                if (_userIdDto.value?.id != null) {
                    getUserInfo(_userIdDto.value!!.id)
                }
            } catch (e: HttpException) {
                _errorMessage.value = "구단주 정보를 가져오는데 실패했습니다."
                _userInfoVisible.value = false
                Log.e(
                    "API_ERROR",
                    "HTTP Error: ${e.code()}, Response: ${e.response()?.errorBody()?.string()}"
                )
            } catch (e: Exception) {
                Log.e("API_ERROR", "Unexpected Error: ${e.message}")
            }
        }
    }

    private fun getUserInfo(ouid: String) {
        viewModelScope.launch {
            try {
                _userInfoDto.value = getUserInfoUseCase.getUserInfo(ouid)
                _userNickName.value = _userInfoDto.value?.nickName
                _userLevel.value = "Lv.${_userInfoDto.value?.level}"
                _userInfoVisible.value = true
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

    private fun requestHideKeyboard() {
        hideKeyboardEvent.value = true
    }

}
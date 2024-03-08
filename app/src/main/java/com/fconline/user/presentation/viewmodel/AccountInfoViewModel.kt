package com.fconline.user.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fconline.user.domain.model.Division
import com.fconline.user.domain.model.MatchType
import com.fconline.user.domain.model.MaxDivision
import com.fconline.user.domain.model.UserId
import com.fconline.user.domain.model.UserInfo
import com.fconline.user.domain.usecase.DivisionUserCase
import com.fconline.user.domain.usecase.MatchTypeUseCase
import com.fconline.user.domain.usecase.UserIdUseCase
import com.fconline.user.domain.usecase.UserInfoUseCase
import com.fconline.user.domain.usecase.MaxDivisionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AccountInfoViewModel @Inject constructor(
    private val userIdUseCase: UserIdUseCase,
    private val userInfoUseCase: UserInfoUseCase,
    private val maxDivisionUseCase: MaxDivisionUseCase,
    private val matchTypeUseCase: MatchTypeUseCase,
    private val divisionUseCase: DivisionUserCase
) : ViewModel() {

    val hideKeyboardEvent = MutableStateFlow<Boolean?>(null)

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage

    private val _userInfoVisible = MutableStateFlow<Boolean?>(null)
    val userInfoVisible: StateFlow<Boolean?> = _userInfoVisible

    private val _userIdDto = MutableStateFlow<UserId?>(null)

    private val _userInfoDto = MutableStateFlow<UserInfo?>(null)

    private val _userNickName = MutableStateFlow<String?>(null)
    val userNickName: StateFlow<String?> = _userNickName

    private val _userLevel = MutableStateFlow<String?>(null)
    val userLevel: StateFlow<String?> = _userLevel

    private val _maxDivision = MutableStateFlow<List<MaxDivision>>(emptyList())
    val maxDivision: StateFlow<List<MaxDivision>> = _maxDivision

    private val _matchTypeList = MutableStateFlow<List<MatchType>>(emptyList())
    val matchTypeList: StateFlow<List<MatchType>> = _matchTypeList

    private val _divisionList = MutableStateFlow<List<Division>>(emptyList())
    val divisionList: StateFlow<List<Division>> = _divisionList

    fun searchUserId(nickName: String) {

        requestHideKeyboard()

        if (nickName.isEmpty()) {
            _toastMessage.value = "구단주 명을 입력해 주세요."
            return
        }


        getMatchType()
        getDivision()

        viewModelScope.launch {
            try {
                userIdUseCase.getUserId(nickName).collect { result ->
                    _userIdDto.value = result
                    if (_userIdDto.value?.id != null) {
                        getUserInfo(_userIdDto.value!!.id)
                        getMaxDivision(_userIdDto.value!!.id)
                    }
                }
            } catch (e: HttpException) {
                _toastMessage.value = "구단주 정보를 가져오는데 실패했습니다."
                _userInfoVisible.value = false
                _maxDivision.value = emptyList()
                Log.e(
                    "API_ERROR",
                    "/id HTTP Error: ${e.code()}, Response: ${e.response()?.errorBody()?.string()}"
                )
            } catch (e: Exception) {
                Log.e("API_ERROR", "/id HTTP Unexpected Error: ${e.message}")
            }
        }
    }

    private fun getMatchType() {
        viewModelScope.launch {
            try {
                matchTypeUseCase.getMatchType().collect { result ->
                    _matchTypeList.value = result
                    Log.e("TESTTEST", "matchTypeList Size : ${_matchTypeList.value.size}")
                }
            } catch (e: HttpException) {
                Log.e(
                    "API_ERROR",
                    "/static/fconline/meta/matchtype HTTP Error: ${e.code()}, Response: ${
                        e.response()?.errorBody()?.string()
                    }"
                )
            } catch (e: Exception) {
                Log.e(
                    "API_ERROR",
                    "/static/fconline/meta/matchtype Unexpected Error: ${e.message}"
                )
            }
        }
    }

    private fun getDivision() {
        viewModelScope.launch {
            try {
                divisionUseCase.getDivision().collect() { result ->
                    _divisionList.value = result
                    Log.e("TESTTEST", "matchTypeList Size : ${_divisionList.value.size}")
                }
            } catch (e: HttpException) {
                Log.e(
                    "API_ERROR",
                    "/static/fconline/meta/division HTTP Error: ${e.code()}, Response: ${
                        e.response()?.errorBody()?.string()
                    }"
                )
            } catch (e: Exception) {
                Log.e(
                    "API_ERROR",
                    "/static/fconline/meta/division Unexpected Error: ${e.message}"
                )
            }
        }
    }

    private fun getUserInfo(ouid: String) {
        viewModelScope.launch {
            try {
                userInfoUseCase.getUserInfo(ouid).collect { result ->
                    _userInfoDto.value = result
                    _userNickName.value = _userInfoDto.value?.nickName
                    _userLevel.value = "Lv.${_userInfoDto.value?.level}"
                    _userInfoVisible.value = true
                }
            } catch (e: HttpException) {
                Log.e(
                    "API_ERROR",
                    "/user/basic HTTP Error: ${e.code()}, Response: ${
                        e.response()?.errorBody()?.string()
                    }"
                )
            } catch (e: Exception) {
                Log.e("API_ERROR", "/user/basic Unexpected Error: ${e.message}")
            }
        }
    }

    private fun getMaxDivision(ouid: String) {
        viewModelScope.launch {
            try {
                maxDivisionUseCase.getMaxDivision(ouid).collect { result ->
                    _maxDivision.value = result
                }
            } catch (e: HttpException) {
                Log.e(
                    "API_ERROR",
                    "/user/maxdivision HTTP Error: ${e.code()}, Response: ${
                        e.response()?.errorBody()?.string()
                    }"
                )
            } catch (e: Exception) {
                Log.e("API_ERROR", "/user/maxdivision Unexpected Error: ${e.message}")
            }
        }
    }

    fun showToast(message: String) {
        _toastMessage.value = message
    }

    fun clearToast() {
        _toastMessage.value = null
    }

    private fun requestHideKeyboard() {
        hideKeyboardEvent.value = true
    }

}

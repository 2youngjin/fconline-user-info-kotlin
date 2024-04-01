package com.fconline.user.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fconline.user.domain.model.Division
import com.fconline.user.domain.model.MatchId
import com.fconline.user.domain.model.MatchType
import com.fconline.user.domain.model.MaxDivision
import com.fconline.user.domain.model.UserId
import com.fconline.user.domain.model.UserInfo
import com.fconline.user.domain.usecase.DivisionUserCase
import com.fconline.user.domain.usecase.MatchIdUseCase
import com.fconline.user.domain.usecase.MatchTypeUseCase
import com.fconline.user.domain.usecase.UserIdUseCase
import com.fconline.user.domain.usecase.UserInfoUseCase
import com.fconline.user.domain.usecase.MaxDivisionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AccountInfoViewModel @Inject constructor(
    private val userIdUseCase: UserIdUseCase,
    private val userInfoUseCase: UserInfoUseCase,
    private val maxDivisionUseCase: MaxDivisionUseCase,
    private val matchTypeUseCase: MatchTypeUseCase,
    private val divisionUseCase: DivisionUserCase,
    private val matchIdUseCase: MatchIdUseCase
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

    private val _divisionList = MutableStateFlow<List<Division>>(emptyList())

    private val _matchId = MutableStateFlow<List<MatchId>>(emptyList())
    val matchId: StateFlow<List<MatchId>> = _matchId

    fun searchUserId(nickName: String) {

        requestHideKeyboard()

        if (nickName.isEmpty()) {
            _toastMessage.value = "구단주 명을 입력해 주세요."
            return
        }

        // 계정 조회
        getMatchType() // 매치 종류 ex) 공식 경기, 감독 모드 등
        getDivision() // 등급 식별자 ex) 챔피 언스, 슈퍼 챔피 언스 등
        userCheck(nickName)
    }

    private fun getMatchType() {
        viewModelScope.launch {
            try {
                matchTypeUseCase.getMatchType().collect { result ->
                    _matchTypeList.value = result
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

    private fun userCheck(nickName: String) {
        viewModelScope.launch {
            try {
                userIdUseCase.getUserId(nickName).collect { result ->
                    _userIdDto.value = result
                    if (_userIdDto.value?.id != null) {
                        getUserInfo(_userIdDto.value!!.id) // 닉네임, 레벨
                        getMaxDivision(_userIdDto.value!!.id) // 레벨
                        getMatchId(
                            ouid = _userIdDto.value!!.id,
                            matchType = 50,
                            offSet = 0,
                            limit = 10
                        ) // 매치 기록 ID
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
                    Log.e("TESTTEST", "1111111111")
                    Log.e("TESTTEST", "TEST1 : ${result.toString()}")
                    Log.e("TESTTEST", "2222222222")
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

    private fun getMatchId(ouid: String, matchType: Int, offSet: Int, limit: Int) {
        viewModelScope.launch {
            try {
                matchIdUseCase.getMatchId(ouid, matchType, offSet, limit).collect() { result ->
                    _matchId.value = result
                }
            } catch (e: HttpException) {
                Log.e(
                    "API_ERROR",
                    "/user/match HTTP Error: ${e.code()}, Response: ${
                        e.response()?.errorBody()?.string()
                    }"
                )
            } catch (e: Exception) {
                Log.e("API_ERROR", "/user/match Unexpected Error: ${e.message}")
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

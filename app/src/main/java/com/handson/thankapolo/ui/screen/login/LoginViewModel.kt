package com.handson.thankapolo.ui.screen.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.handson.domain.data.login.repository.LoginRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {
    private var _signUpData = MutableStateFlow("")
    val signUpData : StateFlow<String>
        get() = _signUpData

    private var _errorData = MutableStateFlow("")
    val errorData : StateFlow<String>
        get() = _errorData

    private var _signInData = MutableStateFlow("")
    val signInData : StateFlow<String>
        get() = _signInData

    fun signUp(email: String, password: String, nickname: String){
        viewModelScope.launch {
            repository.signUp(email, password, nickname)
                .catch { e ->
                    e.message?.let { _errorData.value = "중복된 이메일입니다." }
                }
                .collectLatest {
                    _signUpData.value = it.message
                }
        }
    }

    fun signIn(email: String, password: String, fcmToken: String){
        viewModelScope.launch {
            repository.signIn(email, password, fcmToken)
                .catch { e ->
                    e.message?.let { _errorData.value = "비밀번호가 일치하지 않습니다." }
                }.collectLatest {
                    _signInData.value = it.data.accessToken
                }
        }
    }
}
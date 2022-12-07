package com.handson.thankapolo.ui.screen.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.handson.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel(){

    private var _nicknameData = MutableStateFlow("")
    val nicknameData : StateFlow<String>
        get() = _nicknameData

    init {
        viewModelScope.launch {
            repository.getNickname()
                .catch {  }
                .collectLatest{
                    if (it.success)
                        _nicknameData.value = it.data.nickname
                }
        }
    }

    fun nicknameChange(nickname: String){
        viewModelScope.launch {
            repository.changeNickname(nickname)
                .catch {  }
                .collect{
                    _nicknameData.value = ""
                    _nicknameData.value = it.data.nickname
                }
        }
    }
}
package com.handson.thankapolo.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.handson.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
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
}
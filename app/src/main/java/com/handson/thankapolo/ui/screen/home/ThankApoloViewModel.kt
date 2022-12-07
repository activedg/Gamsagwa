package com.handson.thankapolo.ui.screen.home

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.handson.domain.data.home.Message
import com.handson.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThankApoloViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel(){
    private var _letterData = MutableStateFlow<List<Message>>(emptyList())
    val letterData : StateFlow<List<Message>>
        get() = _letterData

    init {
        viewModelScope.launch {
            repository.getMessageList()
                .catch {  }
                .collect{
                    _letterData.value = it
                }
        }
    }
}
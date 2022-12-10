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

    private var _totalData = mutableListOf<Message>()

    init {
        viewModelScope.launch {
            repository.getMessageList()
                .catch {  }
                .collect{
                    _letterData.value = it
                    _totalData = it as MutableList<Message>
                }
        }
    }

    fun setMessageType(type: Int){
        // type 값에 따라 홈화면에서 보여질 편지 데이터 필터링
        // 0 : 전체, 1: 감사함, 2: 미안함
        when(type){
            0 -> _letterData.value = _totalData
            1 -> _letterData.value = _totalData.filter { m -> m.messageType == "THANK" }
            2 -> _letterData.value = _totalData.filter { m -> m.messageType == "SORRY" }
        }
    }
}
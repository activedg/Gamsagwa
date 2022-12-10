package com.handson.thankapolo.ui.screen.home

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.handson.domain.data.home.Message
import com.handson.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThankApoloViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel(){
    private var _letterData = MutableStateFlow<MutableList<Message>>(mutableListOf())
    val letterData : StateFlow<MutableList<Message>>
        get() = _letterData

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()


    private var _totalData = mutableListOf<Message>()

    private var _currentType = 0

    init {
        viewModelScope.launch {
            repository.getMessageList()
                .catch {  }
                .collect{
                    _letterData.value = it.toMutableList()
                    _totalData = it.toMutableList()
                }
        }
    }

    fun setMessageType(type: Int){
        // type 값에 따라 홈화면에서 보여질 편지 데이터 필터링
        // 0 : 전체, 1: 감사함, 2: 미안함
        when(type){
            0 -> _letterData.value = _totalData
            1 -> _letterData.value = _totalData.filter { m -> m.messageType == "THANK" }.toMutableList()
            2 -> _letterData.value = _totalData.filter { m -> m.messageType == "SORRY" }.toMutableList()
        }
        _currentType = type
    }

    fun deleteMessage(message : Message){
        viewModelScope.launch {
            repository.deleteMessage(message.messageId)
                .catch {  }
                .collectLatest{
                    if (it) {
                        _toastMessage.emit("메시지 삭제에 성공하였습니다.")
                        _totalData.remove(message)
                        _letterData.value = when (_currentType){
                            0 ->  _totalData
                            1 ->  _totalData.filter { m -> m.messageType == "THANK" }.toMutableList()
                            else ->  _totalData.filter { m -> m.messageType == "SORRY" }.toMutableList()
                        }

                    }
                    else _toastMessage.emit("메시지 삭제에 실패하였습니다.")
                }
        }
    }
}
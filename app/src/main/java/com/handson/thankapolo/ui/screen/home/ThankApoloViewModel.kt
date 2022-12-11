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
    private var _curMessage: Message? = null

    private val _successData = MutableStateFlow(false)
    val successData : StateFlow<Boolean>
        get() = _successData

    init {
        getMessageData()
    }

    fun getMessageData(){
        viewModelScope.launch {
            repository.getMessageList()
                .catch {  }
                .collect{
                    _totalData = it.toMutableList()
                    _letterData.value = when (_currentType){
                        0 ->  _totalData
                        1 ->  _totalData.filter { m -> m.messageType == "THANK" }.toMutableList()
                        else ->  _totalData.filter { m -> m.messageType == "SORRY" }.toMutableList()
                    }
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

    fun setCurrentMessage(message: Message){
        _curMessage = message
    }

    fun deleteMessage(){
        viewModelScope.launch {
            _curMessage?.messageId?.let {
                repository.deleteMessage(it)
                    .catch {  }
                    .collectLatest{ b->
                        if (b) {
                            _toastMessage.emit("메시지 삭제에 성공하였습니다.")
                            getMessageData()

                        } else _toastMessage.emit("메시지 삭제에 실패하였습니다.")
                    }
            }
        }
    }

    fun changeVisibility(){
        viewModelScope.launch {
            _curMessage?.messageId?.let {
                repository.changeVisibility(it)
                    .catch { e -> e.message?.let { _toastMessage.emit("숨김 상태 변화에 실패하였습니다.") } }
                    .collectLatest {
                        getMessageData()
                        _toastMessage.emit("숨김 상태 변화에 성공하였습니다.")
                    }
            }
        }
    }

    fun sendMessage(title: String, description: String, receiverEmail: String, messageType: Int, nameBlind: Boolean){
        viewModelScope.launch {
            repository.sendMessage(title, description, receiverEmail, if (messageType == 1) "THANK" else "SORRY", nameBlind)
                .catch { it.message?.let { _toastMessage.emit("이메일이 올바르지 않습니다.") } }
                .collectLatest {
                    _successData.value = it
                    if (it) _toastMessage.emit("메시지 전송에 성공하였습니다.")
                    else _toastMessage.emit("이메일이 올바르지 않습니다.")
                }
        }
    }
}
package com.handson.thankapolo.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
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
    private val repository: ProfileRepository,
    private val firebaseMessaging: FirebaseMessaging
) : ViewModel(){
    private var _nicknameData = MutableStateFlow("")
    val nicknameData : StateFlow<String>
        get() = _nicknameData

    private lateinit var fcmToken : String

    init {
        getNickname()
        firebaseMessaging.token.addOnCompleteListener { task ->
            if (!task.isSuccessful)
                return@addOnCompleteListener
            fcmToken = task.result
            Log.e("fcm Token", fcmToken)
        }
    }

    fun getNickname(){
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
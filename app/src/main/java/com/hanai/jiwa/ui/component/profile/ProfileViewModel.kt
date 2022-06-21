package com.hanai.jiwa.ui.component.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hanai.jiwa.ui.base.BaseViewModel

class ProfileViewModel : BaseViewModel() {
    private val saveBasicProfileLiveDataPrivate = MutableLiveData<Boolean>()
    val saveBasicProfile: LiveData<Boolean> get() = saveBasicProfileLiveDataPrivate

    fun saveBasicProfile() {
        saveBasicProfileLiveDataPrivate.postValue(true)
    }
}
package com.hanai.jiwa.ui.component.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hanai.jiwa.ui.base.BaseViewModel

class ProfileViewModel : BaseViewModel() {
    private val saveBasicProfileLiveDataPrivate = MutableLiveData<Boolean>()
    val saveBasicProfile: LiveData<Boolean> get() = saveBasicProfileLiveDataPrivate

    private val saveBirthDateProfileLiveDataPrivate = MutableLiveData<Boolean>()
    val saveBirthDate: LiveData<Boolean> get() = saveBirthDateProfileLiveDataPrivate

    private val saveMotherProfileLiveDataPrivate = MutableLiveData<Boolean>()
    val saveMotherProfile: LiveData<Boolean> get() = saveMotherProfileLiveDataPrivate

    fun saveBasicProfile() {
        saveBasicProfileLiveDataPrivate.postValue(true)
    }

    fun saveBirthDateProfile() {
        saveBirthDateProfileLiveDataPrivate.postValue(true)
    }

    fun saveMotherProfile() {
        saveMotherProfileLiveDataPrivate.postValue(true)
    }
}
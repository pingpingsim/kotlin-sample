package com.hanai.jiwa.ui.component.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hanai.jiwa.data.DataRepository
import com.hanai.jiwa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel() {
    private val saveBasicProfileLiveDataPrivate = MutableLiveData<Boolean>()
    val saveBasicProfile: LiveData<Boolean> get() = saveBasicProfileLiveDataPrivate

    private val saveBirthDateProfileLiveDataPrivate = MutableLiveData<Boolean>()
    val saveBirthDate: LiveData<Boolean> get() = saveBirthDateProfileLiveDataPrivate

    private val saveMotherProfileLiveDataPrivate = MutableLiveData<Boolean>()
    val saveMotherProfile: LiveData<Boolean> get() = saveMotherProfileLiveDataPrivate

    fun saveBasicProfile(firstName: String, lastName: String) {
        saveBasicProfileLiveDataPrivate.postValue(true)
        createUser()//test
    }

    fun saveBirthDateProfile(day: Int, month: Int, year: Int) {
        saveBirthDateProfileLiveDataPrivate.postValue(true)
    }

    fun saveMotherProfile(day: Int, month: Int, year: Int) {
        saveMotherProfileLiveDataPrivate.postValue(true)
    }

    fun createUser() {
        viewModelScope.launch {

            dataRepository.createUserWithEmailAndPassword("p@mail.com", "pppppp", "ohmy", "123")
                .collect() {
                    val result = it
                }
        }
    }
}
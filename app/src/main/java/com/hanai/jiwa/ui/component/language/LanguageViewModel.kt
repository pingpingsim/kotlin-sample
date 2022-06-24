package com.hanai.jiwa.ui.component.language

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
class LanguageViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel() {
    // language
    private val languageLiveDataPrivate = MutableLiveData<String>()
    val selectedLanguage: LiveData<String> get() = languageLiveDataPrivate

    // language
    private val saveLanguageDataPrivate = MutableLiveData<Boolean>()
    val saveLanguageSuccess: LiveData<Boolean> get() = saveLanguageDataPrivate

    fun selectLanguage(language: String) {
        languageLiveDataPrivate.postValue(language)
    }

    fun saveLanguageSelection() {
        saveLanguageDataPrivate.postValue(true)

        viewModelScope.launch {
            dataRepository.setLanguage(languageLiveDataPrivate.value.toString())
                .collect() { val success = it }
        }
    }
}
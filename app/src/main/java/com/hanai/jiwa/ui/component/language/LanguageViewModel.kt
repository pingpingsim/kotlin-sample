package com.hanai.jiwa.ui.component.language

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hanai.jiwa.ui.base.BaseViewModel

class LanguageViewModel : BaseViewModel() {
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
        //save to local cache or server accordingly
    }
}
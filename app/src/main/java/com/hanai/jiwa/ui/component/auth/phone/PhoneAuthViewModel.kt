package com.hanai.jiwa.ui.component.auth.phone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hanai.jiwa.data.DataRepository
import com.hanai.jiwa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhoneAuthViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel() {
    // phone number
    private val mobileNoLiveDataPrivate = MutableLiveData<String>()
    val mobileNumber: LiveData<String> get() = mobileNoLiveDataPrivate

    // verification code
    private val vfyCodeLiveDataPrivate = MutableLiveData<String>()
    val verificationCode: LiveData<String> get() = vfyCodeLiveDataPrivate

    fun setMobileNumber(mobileNumber: String) {
        mobileNoLiveDataPrivate.postValue(mobileNumber)
    }

    fun setVerificationCode(verificationCode: String) {
        vfyCodeLiveDataPrivate.postValue(verificationCode)
    }
}
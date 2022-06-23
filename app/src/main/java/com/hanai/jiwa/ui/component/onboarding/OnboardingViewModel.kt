package com.hanai.jiwa.ui.component.onboarding

import com.hanai.jiwa.data.DataRepository
import com.hanai.jiwa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel() {
}
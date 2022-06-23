package com.hanai.jiwa.ui.component.dashboard.resources

import com.hanai.jiwa.data.DataRepository
import com.hanai.jiwa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResourcesViewModel @Inject constructor(private val dataRepository: DataRepository) :
    BaseViewModel() {
}
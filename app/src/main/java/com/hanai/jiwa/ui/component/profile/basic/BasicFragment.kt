package com.hanai.jiwa.ui.component.profile.basic

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.hanai.jiwa.databinding.FragmentProfileBasicBinding
import com.hanai.jiwa.ui.base.BaseFragment
import com.hanai.jiwa.ui.component.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasicFragment() : BaseFragment<FragmentProfileBasicBinding, ProfileViewModel>() {
    private val activityViewModel: ProfileViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root
        binding.apply {
            //txtData.text = "Something"
            btnNextStep.setOnClickListener {
                activityViewModel.saveBasicProfile()
            }
        }
        //viewModel.textMethod()
    }
}
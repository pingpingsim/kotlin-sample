package com.hanai.jiwa.ui.component.profile.motherinfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.hanai.jiwa.databinding.FragmentProfileMotherInfoBinding
import com.hanai.jiwa.ui.base.BaseFragment
import com.hanai.jiwa.ui.component.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MotherInfoFragment() : BaseFragment<FragmentProfileMotherInfoBinding, ProfileViewModel>() {
    private val activityViewModel: ProfileViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root
        binding.apply {
            btnGetStarted.setOnClickListener {
                activityViewModel.saveMotherProfile()
            }
        }
    }
}
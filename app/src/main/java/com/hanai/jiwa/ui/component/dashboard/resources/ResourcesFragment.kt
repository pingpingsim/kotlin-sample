package com.hanai.jiwa.ui.component.dashboard.resources

import android.os.Bundle
import android.view.View
import com.hanai.jiwa.databinding.FragmentResourcesBinding
import com.hanai.jiwa.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResourcesFragment : BaseFragment<FragmentResourcesBinding, ResourcesViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root
        binding.apply {
            //txtData.text = "Something"
        }
        //viewModel.textMethod()
    }
}
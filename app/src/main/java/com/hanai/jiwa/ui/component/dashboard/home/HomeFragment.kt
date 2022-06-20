package com.hanai.jiwa.ui.component.dashboard.home

import android.os.Bundle
import android.view.View
import com.hanai.jiwa.databinding.FragmentHomeBinding
import com.hanai.jiwa.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root
        binding.apply {
            //txtData.text = "Something"
        }
        //viewModel.textMethod()
    }
}
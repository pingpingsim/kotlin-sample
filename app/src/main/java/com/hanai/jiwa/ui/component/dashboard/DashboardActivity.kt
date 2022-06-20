package com.hanai.jiwa.ui.component.dashboard

import android.os.Bundle
import com.hanai.jiwa.databinding.ActivityDashboardBinding
import com.hanai.jiwa.ui.base.BaseActivity

class DashboardActivity : BaseActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initViewBinding() {
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun observeViewModel() {
    }
}
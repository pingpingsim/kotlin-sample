package com.hanai.jiwa.ui.component.dashboard

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.hanai.jiwa.R
import com.hanai.jiwa.databinding.ActivityDashboardBinding
import com.hanai.jiwa.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomNavView()
    }

    override fun initViewBinding() {
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.apply { toolbar.title = "" }
    }

    override fun observeViewModel() {
    }


    private fun initBottomNavView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.navView, navController)
        //binding.navView.itemIconTintList = null
    }
}
package com.hanai.jiwa.ui.component.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.hanai.jiwa.SPLASH_DELAY
import com.hanai.jiwa.databinding.ActivitySplashBinding
import com.hanai.jiwa.ui.base.BaseActivity
import com.hanai.jiwa.ui.component.dashboard.DashboardActivity
import com.hanai.jiwa.ui.component.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToNextScreen()
    }

    override fun initViewBinding() {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun observeViewModel() {
    }

    /**
     * TODO: Check & perform redirection to next screen
     */
    private fun navigateToNextScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val landingScreen = Intent(this, DashboardActivity::class.java)
            startActivity(landingScreen)
            finish()
        }, SPLASH_DELAY.toLong())
    }
}
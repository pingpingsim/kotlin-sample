package com.hanai.jiwa.ui.component.language

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.hanai.jiwa.databinding.ActivityLanguageBinding
import com.hanai.jiwa.ui.base.BaseActivity
import com.hanai.jiwa.ui.component.onboarding.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LanguageActivity : BaseActivity() {
    private lateinit var binding: ActivityLanguageBinding
    private val languageViewModel: LanguageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initViewBinding() {
        binding = ActivityLanguageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.apply {
            binding.nextButton.setOnClickListener {
                val intent = Intent(this@LanguageActivity, OnboardingActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun observeViewModel() {
    }
}
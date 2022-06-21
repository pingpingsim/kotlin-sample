package com.hanai.jiwa.ui.component.language

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.hanai.jiwa.LANGUAGE_BM
import com.hanai.jiwa.LANGUAGE_EN
import com.hanai.jiwa.R
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
                languageViewModel.saveLanguageSelection()
            }

            binding.lgEnglishPanel.setOnClickListener {
                languageViewModel.selectLanguage(LANGUAGE_EN)
            }

            binding.lgMalayPanel.setOnClickListener {
                languageViewModel.selectLanguage(LANGUAGE_BM)
            }
        }
    }

    override fun observeViewModel() {
        observeLanguageChange()
        observeSaveLanguageSetting()
    }

    private fun observeSaveLanguageSetting() {
        languageViewModel.saveLanguageSuccess.observe(this, Observer {
            if (it) {
                val intent = Intent(this@LanguageActivity, OnboardingActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Popup error message
            }
        })
    }

    /**
     * Control display accordingly based on language selection made by end-user
     */
    private fun observeLanguageChange() {
        languageViewModel.selectedLanguage.observe(this, Observer {
            if (it.equals(LANGUAGE_EN, ignoreCase = true)) {
                enableEnglishPanel(true)
                enableMalayPanel(false)
                enableNextButton()
            } else {
                enableMalayPanel(true)
                enableEnglishPanel(false)
                enableNextButton()
            }
        })
    }

    private fun enableEnglishPanel(enable: Boolean) {
        binding.lgEnglishPanel.setBackgroundColor(
            if (enable) ContextCompat.getColor(
                this,
                R.color.language_panel_active
            ) else ContextCompat.getColor(this, R.color.language_panel_inactive)
        )
        binding.englishLblTextView.setTextColor(
            if (enable) ContextCompat.getColor(
                this,
                R.color.white
            ) else ContextCompat.getColor(this, R.color.black)
        )
    }

    private fun enableMalayPanel(enable: Boolean) {
        binding.lgMalayPanel.setBackgroundColor(
            if (enable) ContextCompat.getColor(
                this,
                R.color.language_panel_active
            ) else ContextCompat.getColor(this, R.color.language_panel_inactive)
        )
        binding.malayLblTextView.setTextColor(
            if (enable) ContextCompat.getColor(
                this,
                R.color.white
            ) else ContextCompat.getColor(this, R.color.black)
        )
    }

    private fun enableNextButton() {
        binding.nextButton.setBackgroundColor(R.color.button_enabled)
        binding.nextButton.isEnabled = true
        binding.nextButton.isClickable = true
    }
}
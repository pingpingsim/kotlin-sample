package com.hanai.jiwa.ui.component.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.viewpager.widget.ViewPager
import com.hanai.jiwa.R
import com.hanai.jiwa.data.dto.onboarding.OnboardingContent
import com.hanai.jiwa.databinding.ActivityOnboardingBinding
import com.hanai.jiwa.ui.base.BaseActivity
import com.hanai.jiwa.ui.component.dashboard.DashboardActivity
import com.hanai.jiwa.ui.component.onboarding.adapter.OnboardingListAdapter
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : BaseActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var onboardingListAdapter: OnboardingListAdapter
    private val onboardingViewModel: OnboardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initOnboardingContentViewPager()
    }

    override fun initViewBinding() {
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.apply {
            // skip button event handler
            btnSkip.setOnClickListener {
                val dashboardIntent = Intent(this@OnboardingActivity, DashboardActivity::class.java)
                startActivity(dashboardIntent)
                finish()
            }
        }
    }

    override fun observeViewModel() {
    }

    fun initOnboardingContentViewPager() {
        val dotsIndicator = findViewById<SpringDotsIndicator>(R.id.dot_indicator)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        onboardingListAdapter =
            OnboardingListAdapter(onboardingViewModel, getOnboardingContent())//listOf()
        viewPager.adapter = onboardingListAdapter
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener)
        dotsIndicator.attachTo(viewPager)
    }

    fun getOnboardingContent(): List<OnboardingContent> {
        var onboardingContentList = ArrayList<OnboardingContent>()
        onboardingContentList.add(
            OnboardingContent(
                getString(R.string.onboarding_healthcare_title),
                getString(R.string.onboarding_healthcare_desc),
                R.mipmap.maternity
            )
        )
        onboardingContentList.add(
            OnboardingContent(
                getString(R.string.onboarding_guidance_title),
                getString(R.string.onboarding_guidance_desc),
                R.mipmap.phone
            )
        )
        onboardingContentList.add(
            OnboardingContent(
                getString(R.string.onboarding_lifeline_title),
                getString(R.string.onboarding_lifeline_desc),
                R.mipmap.lifeline
            )
        )
        return onboardingContentList
    }

    var viewPagerPageChangeListener: ViewPager.OnPageChangeListener =
        object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                // your logic here
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // your logic here
            }

            override fun onPageSelected(position: Int) {
                // your logic here
            }
        }
}
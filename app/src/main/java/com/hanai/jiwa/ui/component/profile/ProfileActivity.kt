package com.hanai.jiwa.ui.component.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.hanai.jiwa.R
import com.hanai.jiwa.databinding.ActivityProfileBinding
import com.hanai.jiwa.ui.base.BaseFragmentActivity
import com.hanai.jiwa.ui.component.dashboard.DashboardActivity
import com.hanai.jiwa.ui.component.onboarding.OnboardingActivity
import com.hanai.jiwa.ui.component.profile.basic.BasicFragment
import com.hanai.jiwa.ui.component.profile.birthdate.BirthDateFragment
import com.hanai.jiwa.ui.component.profile.motherinfo.MotherInfoFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : BaseFragmentActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun initViewBinding() {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.apply {
            // Instantiate a ViewPager2 and a PagerAdapter.
            viewPager = findViewById(R.id.pager)
            viewPager.isUserInputEnabled = false

            // The pager adapter, which provides the pages to the view pager widget.
            val pagerAdapter = ScreenSlidePagerAdapter(this@ProfileActivity)
            viewPager.adapter = pagerAdapter
        }
    }

    override fun observeViewModel() {
        profileViewModel.saveBasicProfile.observe(this, Observer {
            binding.pager.currentItem = 1
        })
        profileViewModel.saveBirthDate.observe(this, Observer {
            binding.pager.currentItem = 2
        })
        profileViewModel.saveMotherProfile.observe(this, Observer {
            val intent = Intent(this@ProfileActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return BasicFragment()
                1 -> return BirthDateFragment()
                else -> return MotherInfoFragment()
            }
        }
    }
}
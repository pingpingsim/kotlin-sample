package com.hanai.jiwa.ui.component.onboarding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.hanai.jiwa.R
import com.hanai.jiwa.data.dto.onboarding.OnboardingContent
import com.hanai.jiwa.ui.component.onboarding.OnboardingViewModel

class OnboardingListAdapter(
    private val onboardingViewModel: OnboardingViewModel,
    private val onboardingContentList: List<OnboardingContent>
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val contentItem: OnboardingContent = onboardingContentList[position]

        val view = LayoutInflater.from(container.context).inflate(
            R.layout.onboarding_viewpager_item, container,
            false
        )
        var titleTextView: TextView = view.findViewById(R.id.txt_title)
        var descTextView: TextView = view.findViewById(R.id.txt_desc)
        titleTextView.text = contentItem.title
        descTextView.text = contentItem.desc

        container.addView(view, position)

        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return onboardingContentList.size
    }
}
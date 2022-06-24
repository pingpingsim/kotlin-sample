package com.hanai.jiwa.ui.base

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.hanai.jiwa.LANGUAGE_EN
import com.hanai.jiwa.SHARED_PREFERENCES_LANGUAGE
import com.hanai.jiwa.ui.component.auth.phone.PhoneAuthViewModel
import com.hanai.jiwa.utils.ContextUtils
import java.util.*

abstract class BaseActivity : AppCompatActivity() {
    abstract fun initViewBinding()
    abstract fun observeViewModel()
    private val viewModel: PhoneAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        observeViewModel()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun attachBaseContext(newBase: Context) {
        val sharedPref = newBase.getSharedPreferences(SHARED_PREFERENCES_LANGUAGE, 0)
        val language = sharedPref.getString(SHARED_PREFERENCES_LANGUAGE, LANGUAGE_EN) ?: LANGUAGE_EN

        val localeToSwitchTo = Locale(language)
        val localeUpdatedContext: ContextWrapper =
            ContextUtils.updateLocale(newBase, localeToSwitchTo)

        super.attachBaseContext(localeUpdatedContext)
    }
}
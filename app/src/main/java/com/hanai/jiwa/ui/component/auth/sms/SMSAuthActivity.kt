package com.hanai.jiwa.ui.component.auth.sms

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.hanai.jiwa.databinding.ActivitySmsAuthBinding
import com.hanai.jiwa.ui.base.BaseActivity
import com.hanai.jiwa.ui.component.auth.phone.PhoneAuthViewModel
import com.hanai.jiwa.ui.component.profile.ProfileActivity

class SMSAuthActivity : BaseActivity() {
    private lateinit var binding: ActivitySmsAuthBinding
    private val phoneAuthViewModel: PhoneAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initViewBinding() {
        binding = ActivitySmsAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.apply {

            btnSmsYes.setOnClickListener {
                val intent = Intent(this@SMSAuthActivity, ProfileActivity::class.java)
                startActivity(intent)
                finish()
            }

            btnSmsNo.setOnClickListener {

            }
        }
    }

    override fun observeViewModel() {
    }
}
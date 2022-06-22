package com.hanai.jiwa.ui.component.auth.phone

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.hanai.jiwa.databinding.ActivityPhoneAuthBinding
import com.hanai.jiwa.ui.base.BaseActivity
import com.hanai.jiwa.ui.component.auth.sms.SMSAuthActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneAuthActivity : BaseActivity() {
    private lateinit var binding: ActivityPhoneAuthBinding
    private val phoneAuthViewModel: PhoneAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initViewBinding() {
        binding = ActivityPhoneAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.apply {
            val mobileNumber = mobileNumberTextField.editText?.text.toString()
            val verificationCode = enterCodeTextField.editText?.text.toString()
            btnSendCode.setOnClickListener {
                phoneAuthViewModel.setMobileNumber(mobileNumber)

                val intent = Intent(this@PhoneAuthActivity, SMSAuthActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun observeViewModel() {
    }
}
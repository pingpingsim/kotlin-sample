package com.hanai.jiwa.ui.component.phone_auth

import android.os.Bundle
import androidx.activity.viewModels
import com.hanai.jiwa.databinding.ActivityPhoneAuthBinding
import com.hanai.jiwa.ui.base.BaseActivity
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
            //val inputText = outlinedTextField.editText?.text.toString()

        }
    }

    override fun observeViewModel() {
    }
}
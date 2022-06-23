package com.hanai.jiwa.ui.component.auth.phone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hanai.jiwa.databinding.ActivityPhoneAuthBinding
import com.hanai.jiwa.ui.base.BaseActivity
import com.hanai.jiwa.ui.component.auth.sms.SMSAuthActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class PhoneAuthActivity : BaseActivity() {
    private lateinit var binding: ActivityPhoneAuthBinding
    private val phoneAuthViewModel: PhoneAuthViewModel by viewModels()
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    // Firebase
    private lateinit var auth: FirebaseAuth
    private var storedVerificationId: String = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        initCallback()
    }

    override fun initViewBinding() {
        binding = ActivityPhoneAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSendCode.setOnClickListener { sendCode() }
        binding.btnVerifyCode.setOnClickListener { verifyCode() }
    }

    private fun sendCode() {
        phoneAuthViewModel.setMobileNumber(
            binding.mobileNumberTextField?.editText?.text?.trim().toString(),
        )
    }

    private fun verifyCode() {
        phoneAuthViewModel.setVerificationCode(
            binding.enterCodeTextField?.editText?.text?.trim().toString()
        )
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun initCallback() {
        // Initialize phone auth callbacks
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }

                // Show a message and update the UI
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token
                enableEnterCodeInputField()
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    override fun observeViewModel() {
        phoneAuthViewModel.mobileNumber.observe(this, Observer {
            //startPhoneNumberVerification(it)
            enableEnterCodeInputField()
        })

        phoneAuthViewModel.verificationCode.observe(this, Observer {
//            val credential = PhoneAuthProvider.getCredential(storedVerificationId, it)
//            signInWithPhoneAuthCredential(credential)

            val intent = Intent(this@PhoneAuthActivity, SMSAuthActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    private fun enableEnterCodeInputField() {
        binding.btnVerifyCode.visibility = View.VISIBLE
        binding.btnSendCode.visibility = View.GONE
        binding.enterCodeTextField.visibility = View.VISIBLE
    }

    companion object {
        private const val TAG = "PhoneAuthActivity"
    }
}
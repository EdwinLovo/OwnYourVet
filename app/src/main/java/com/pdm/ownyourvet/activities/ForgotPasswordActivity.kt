package com.pdm.ownyourvet.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.goToActivity
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        button_GoLogin_Forgot.setOnClickListener {
            goToActivity<LoginActivity>()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}

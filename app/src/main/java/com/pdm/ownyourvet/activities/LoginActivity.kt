package com.pdm.ownyourvet.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.pdm.ownyourvet.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var newAccount: Button
    lateinit var forgotPassword:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        newAccount = button_signUp
        forgotPassword = textView_ForgotPassword

        newAccount.setOnClickListener {
            var intent:Intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        forgotPassword.setOnClickListener {
            var intent:Intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

    }
}

package com.pdm.ownyourvet.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.pdm.ownyourvet.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()

        button_LogIn.setOnClickListener {
            val email = editText_Email.text.toString()
            val password = editText_Password.text.toString()
            if (isValidEmail(email) && isValidPassword(password)){
                logInByEmail(email, password)
            } else {
                toast("Please make sure all the data is correct")
            }
        }

        textView_ForgotPassword.setOnClickListener {
            goToActivity<ForgotPasswordActivity> {  }
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }

        button_CreateAccount.setOnClickListener {
            goToActivity<SignUpActivity> {  }
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }

        editText_Email.validate {
            editText_Email.error = if (isValidEmail(it)) null else "Email is not valid"
        }

        editText_Password.validate {
            editText_Password.error = if (isValidPassword(it)) null else "Password must contain 1 lowercase, 1 uppercase, 1 number, 1 special character and 6 characters length at least"
        }

    }

    private fun logInByEmail(email:String, password:String){
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task ->
                    if (task.isSuccessful){
                        if (mAuth.currentUser!!.isEmailVerified){
                            toast("User is now logged in.")
                        } else {
                            toast("User must confirm email first.")
                        }
                    } else {
                        toast("An unexpected error occurred, please try again.")
                    }
                }
    }
}

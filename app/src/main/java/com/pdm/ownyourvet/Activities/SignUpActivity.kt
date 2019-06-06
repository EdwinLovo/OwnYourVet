package com.pdm.ownyourvet.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.pdm.ownyourvet.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        button_GoLogin_SignUp.setOnClickListener {
            goToActivity<LoginActivity>{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        button_signUp_SignUp.setOnClickListener {
            val email = editText_Email_SignUp.text.toString()
            val password = editText_Password_SignUp.text.toString()
            val confirmPassword = editText_ConfirmPassword_SignUp.text.toString()
            if (isValidEmail(email) && isValidPassword(password) && isValidConfirmPassword(password, confirmPassword)){
                signUpByEmail(email, password)
            } else {
                toast("Please make sure all the data is correct")
            }
        }

        editText_Email_SignUp.validate {
            editText_Email_SignUp.error = if (isValidEmail(it)) null else "Email is not valid"
        }

        editText_Password_SignUp.validate {
            editText_Password_SignUp.error = if (isValidPassword(it)) null else "Password must contain 1 lowercase, 1 uppercase, 1 number, 1 special character and 6 characters length at least"
        }

        editText_ConfirmPassword_SignUp.validate {
            editText_ConfirmPassword_SignUp.error = if (isValidConfirmPassword(editText_Password_SignUp.text.toString(), it)) null else "Confirm password does not match with Password"
        }
    }

    private fun signUpByEmail(email:String, password:String){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        mAuth.currentUser!!.sendEmailVerification().addOnCompleteListener(this){
                            toast("An email has been sent to you. Please confirm before Sign In.")
                            goToActivity<LoginActivity>{
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        }
                    } else {
                        toast("An unexpected error occurred, please try again.")
                        val e = task.exception as FirebaseAuthException
                        toast( "Failed Registration: " + e.message)
                    }
                }
    }
}

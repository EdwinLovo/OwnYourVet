package com.pdm.ownyourvet

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.regex.Pattern

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, message, duration).show()

fun Activity.toast(resourceId: Int, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, resourceId, duration).show()

fun ViewGroup.inflate(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false)

inline fun <reified T: Activity> Activity.goToActivity(noinline init: Intent.() -> Unit = {}){
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}

fun EditText.validate(validation: (String) -> Unit){
    this.addTextChangedListener((object : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            validation(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    }))
}

fun Activity.isValidEmail(email: String):Boolean{
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}

fun Activity.isValidPassword(password: String):Boolean{
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$"
    val pattern = Pattern.compile(passwordPattern)
    return pattern.matcher(password).matches()
}

fun Activity.isValidConfirmPassword(password: String, confirmPassword: String):Boolean{
    return password == confirmPassword
}

fun createUserByType(){
     val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
     val store: FirebaseFirestore = FirebaseFirestore.getInstance()
    val usersDBRef: CollectionReference = store.collection("users")
    val currentUser: FirebaseUser = mAuth.currentUser!!

    val newUser = HashMap<String, Any>()
    newUser["uid"] = currentUser.uid
    newUser["admin"] = false

    usersDBRef.add(newUser)
        .addOnFailureListener {
        }
        .addOnCompleteListener{
        }
}


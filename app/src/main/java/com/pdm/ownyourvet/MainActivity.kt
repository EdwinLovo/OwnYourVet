package com.pdm.ownyourvet

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.pdm.ownyourvet.Activities.LoginActivity
import com.pdm.ownyourvet.Fragments.ChatFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ChatFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val mAuth: FirebaseAuth by lazy {FirebaseAuth.getInstance()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chatFragment = ChatFragment()
        supportFragmentManager.beginTransaction().add(R.id.framelayoutContainer,chatFragment).commit()

        /*button_SignOut_MainActivity.setOnClickListener {
            mAuth.signOut()
            goToActivity<LoginActivity>()
        }*/
    }
}

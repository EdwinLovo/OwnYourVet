package com.pdm.ownyourvet.Fragments.AdminFragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Room.Entities.User
import com.pdm.ownyourvet.ViewModels.HomeAdminViewModel
import com.pdm.ownyourvet.isConnected

class HomeAdminFragment : Fragment() {
    private val mAuth:FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var currentUser: FirebaseUser
    lateinit var homeViewModel:HomeAdminViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home_admin, container, false)
        homeViewModel = ViewModelProviders.of(this).get(HomeAdminViewModel::class.java)
        if(isConnected(view.context)){
            homeViewModel.sendUser(User(mAuth.currentUser!!.uid,mAuth.currentUser!!.email!!,"1"))
        }
        return view
    }


}

package com.pdm.ownyourvet.Fragments.ClientFragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Room.Entities.User
import com.pdm.ownyourvet.ViewModels.HomeAdminViewModel
import com.pdm.ownyourvet.isConnected

class HomeFragment : Fragment() {
    lateinit var homeViewModel: HomeAdminViewModel
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProviders.of(this).get(HomeAdminViewModel::class.java)
        if(isConnected(view.context)){
            homeViewModel.sendUser(User(mAuth.currentUser!!.uid,mAuth.currentUser!!.email!!,"0"))
        }
        return view
    }


}

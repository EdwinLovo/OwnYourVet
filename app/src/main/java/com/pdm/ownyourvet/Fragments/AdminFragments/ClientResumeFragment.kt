package com.pdm.ownyourvet.Fragments.AdminFragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Utils.NavigationHelper

class ClientResumeFragment : Fragment() {

    lateinit var navigationHelper: NavigationHelper
    lateinit var myView:View
    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationHelper = context as NavigationHelper
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_client_resume, container, false)
        Log.d("CUSTOM","from fragment ${navigationHelper.getUserId()}")
        return myView
    }


}

package com.pdm.ownyourvet.Fragments.AdminFragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Utils.NavigationHelper
import com.pdm.ownyourvet.ViewModels.HomeAdminViewModel
import com.pdm.ownyourvet.isConnected
import kotlinx.android.synthetic.main.fragment_client_resume.*

class ClientResumeFragment : Fragment() {

    lateinit var navigationHelper: NavigationHelper
    lateinit var myView:View
    lateinit var homeAdminViewModel: HomeAdminViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        homeAdminViewModel = ViewModelProviders.of(this).get(HomeAdminViewModel::class.java)
        homeAdminViewModel.userLiveData.observe(this, Observer {
//            Log.d("CUSTOM","listened ${it.names}")
            et_client_email.setText(it.email)
            et_client_direction.setText(it.direction)
            et_client_name.setText(it.names)
        })
        super.onCreate(savedInstanceState)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationHelper = context as NavigationHelper
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_client_resume, container, false)
//        Log.d("CUSTOM",navigationHelper.getUserId())
        if(isConnected(myView.context))
            homeAdminViewModel.retreiveUserById(navigationHelper.getUserId())
        return myView
    }


}

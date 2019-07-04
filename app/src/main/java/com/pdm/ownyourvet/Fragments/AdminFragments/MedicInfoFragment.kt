package com.pdm.ownyourvet.Fragments.AdminFragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Utils.FragmentHelper
import com.pdm.ownyourvet.Utils.NavigationHelper
import com.pdm.ownyourvet.ViewModels.HomeAdminViewModel
import com.pdm.ownyourvet.isConnected
import kotlinx.android.synthetic.main.fragment_medic_info.view.*


class MedicInfoFragment : Fragment(),FragmentHelper {



    lateinit var navigationHelper: NavigationHelper
    lateinit var homeAdminViewModel:HomeAdminViewModel
    lateinit var myView:View
    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationHelper = context as NavigationHelper
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeAdminViewModel = ViewModelProviders.of(this).get(HomeAdminViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_medic_info, container, false)
        homeAdminViewModel.userLiveData.observe(this, Observer {
            myView.et_medic_email.setText(it.email)
            myView.et_medic_name.setText(it.names)
            myView.et_medic_direction.setText(it.direction)

        })
        myView.bt_medic_update.setOnClickListener {
            if(isConnected(myView.context))
                homeAdminViewModel.updateUser(
                        navigationHelper.getUserId(),
                        myView.et_medic_email.text.toString(),
                        myView.et_medic_name.text.toString(),
                        myView.et_medic_direction.text.toString(),
                        this
                )
        }
        if(isConnected(myView.context))
            homeAdminViewModel.retreiveUserById(navigationHelper.getUserId())
        return myView
    }
    override fun executeAfter(customMsg: String?) {
        Toast.makeText(myView.context,"Operacion exitosa",Toast.LENGTH_SHORT).show()
    }

}

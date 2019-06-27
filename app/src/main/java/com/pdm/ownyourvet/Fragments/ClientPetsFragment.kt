package com.pdm.ownyourvet.Fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.pdm.ownyourvet.Adapters.pets.PetsAdapter
import com.pdm.ownyourvet.Network.Models.pets.Patient
import com.pdm.ownyourvet.Network.Models.pets.Pet

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Utils.NavigationHelper
import com.pdm.ownyourvet.ViewModels.HomeAdminViewModel
import com.pdm.ownyourvet.isConnected
import kotlinx.android.synthetic.main.fragment_client_pets.view.*


class ClientPetsFragment : Fragment() {
    private lateinit var adminViewModel: HomeAdminViewModel
    lateinit var navigationHelper: NavigationHelper
    lateinit var myView:View
    lateinit var petsAdapter: PetsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationHelper = context as NavigationHelper

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        petsAdapter = object :PetsAdapter(){
            override fun setClickListener(itemView: View, patient: Patient) {
                Log.d("CUSTOM","clicked")
            }

        }
        adminViewModel = ViewModelProviders.of(this).get(HomeAdminViewModel::class.java)
        adminViewModel.petLiveData.observe(this, Observer {
            petsAdapter.updateList(it)
        })

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        myView =  inflater.inflate(R.layout.fragment_client_pets, container, false)
        if(isConnected(myView.context))
            adminViewModel.getPetsOf(navigationHelper.getUserId())

        myView.rv_admin_client_pets.apply {
            setHasFixedSize(true)
            adapter = petsAdapter
            layoutManager = GridLayoutManager(myView.context,2)
        }
        myView.floating_action_button.setOnClickListener {
            val nextAction = ClientPetsFragmentDirections.nextAction()
            Navigation.findNavController(it).navigate(nextAction)

        }
        return myView
    }


}

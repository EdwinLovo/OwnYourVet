package com.pdm.ownyourvet.Fragments.AdminFragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdm.ownyourvet.Adapters.users.UserAdapter

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Room.Entities.User
import com.pdm.ownyourvet.ViewModels.HomeAdminViewModel
import com.pdm.ownyourvet.isConnected
import kotlinx.android.synthetic.main.fragment_medics.view.*

class MedicsFragment : Fragment() {

    lateinit var userAdapter:UserAdapter
    lateinit var homeAdminViewModel: HomeAdminViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeAdminViewModel = ViewModelProviders.of(this).get(HomeAdminViewModel::class.java)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_medics, container, false)

        homeAdminViewModel.adminsLiveData.observe(this, Observer {
            Log.d("CUSTOM","Observed!!")
            userAdapter.updateList(it)
        })
        if(isConnected(view.context))
            homeAdminViewModel.retrieveAdmins()

        userAdapter = object:UserAdapter(){
            override fun setClickListener(itemView: View, user: User) {
                Toast.makeText(view.context,"Item clicked!",Toast.LENGTH_SHORT).show()
            }
        }
        view.rv_admin_medic_list.apply {
            setHasFixedSize(true)
            adapter = userAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
        view.et_admin_search_medic.addTextChangedListener {
//            Log.d("CUSTOM",it!!.toString())
            if(isConnected(view.context)) {
                if(it.toString().isEmpty()) {
                    Log.d("CUSTOM", "empty")
                    homeAdminViewModel.retrieveAdmins()
                }
                else homeAdminViewModel.retrieveUsersByEmail(it.toString())
            }else Toast.makeText(view.context,"No internet connection!",Toast.LENGTH_SHORT).show()
        }

        return view
    }


}

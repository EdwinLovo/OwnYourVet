package com.pdm.ownyourvet.Fragments.AdminFragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdm.ownyourvet.Adapters.users.UserAdapter

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Room.Entities.User
import com.pdm.ownyourvet.ViewModels.HomeAdminViewModel
import com.pdm.ownyourvet.isConnected
import kotlinx.android.synthetic.main.fragment_admin_client_list.view.*

class AdminClientListFragment : Fragment() {
    private lateinit var adminViewModel:HomeAdminViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adminViewModel = ViewModelProviders.of(this).get(HomeAdminViewModel::class.java)

        adminViewModel.getUserByType("0").observe(this, Observer {
            userAdapter.updateList(it)
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_admin_client_list, container, false)
        userAdapter = object:UserAdapter(){
            override fun setClickListener(itemView: View, disease: User) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        view.rv_client_list.apply {
            setHasFixedSize(true)
            adapter = userAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
        if(isConnected(view.context)) adminViewModel.retrieveClients()
        return view

    }


}

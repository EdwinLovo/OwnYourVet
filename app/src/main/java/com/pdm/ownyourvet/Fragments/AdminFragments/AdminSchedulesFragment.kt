package com.pdm.ownyourvet.Fragments.AdminFragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdm.ownyourvet.Adapters.schedules.ScheduleAdapter
import com.pdm.ownyourvet.Network.Models.schedules.Schedule

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Utils.NavigationHelper
import com.pdm.ownyourvet.ViewModels.SchedulesViewModel
import com.pdm.ownyourvet.isConnected
import kotlinx.android.synthetic.main.fragment_admin_schedules.view.*

class AdminSchedulesFragment : Fragment() {

    lateinit var schedulesViewModel: SchedulesViewModel
    lateinit var scheduleAdapter: ScheduleAdapter
    lateinit var navigationHelper: NavigationHelper

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationHelper = context as NavigationHelper
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        schedulesViewModel = ViewModelProviders.of(this).get(SchedulesViewModel::class.java)
        schedulesViewModel.schedulesLiveData.observe(this, Observer {
          scheduleAdapter.updateList(it)
        })
    }

    override fun onResume() {
        super.onResume()
        schedulesViewModel.schedulesLiveData.observe(this, Observer {
            scheduleAdapter.updateList(it)
        })

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

       val view = inflater.inflate(R.layout.fragment_admin_schedules, container, false)
        scheduleAdapter = object: ScheduleAdapter(){
            override fun setClickListener(itemView: View, patient: Schedule) {
                Toast.makeText(view.context,"Clicked!",Toast.LENGTH_SHORT).show()
            }

        }
        view.rv_admin_schedules.apply {
            setHasFixedSize(true)
            adapter = scheduleAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
        view.bt_add_schedule.setOnClickListener {
            val nextAction = AdminSchedulesFragmentDirections.nextAction()
            nextAction.userId = navigationHelper.getUserId()
            Navigation.findNavController(view).navigate(nextAction)
        }

        if(isConnected(view.context))
            schedulesViewModel.retrieveAllSchedules(navigationHelper.getUserId())
        return view
    }


}

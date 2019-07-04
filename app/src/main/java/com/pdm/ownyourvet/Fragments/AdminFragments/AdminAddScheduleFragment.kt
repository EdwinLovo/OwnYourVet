package com.pdm.ownyourvet.Fragments.AdminFragments


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Utils.FragmentHelper
import com.pdm.ownyourvet.ViewModels.SchedulesViewModel
import com.pdm.ownyourvet.isConnected
import kotlinx.android.synthetic.main.fragment_admin_add_schedule.view.*
import java.text.SimpleDateFormat
import java.util.*

class AdminAddScheduleFragment : Fragment(),FragmentHelper {
    override fun executeAfter(customMsg: String?) {
        if(customMsg==null)
            Toast.makeText(myView.context,"Operacion Exitosa",Toast.LENGTH_SHORT).show()
        else Toast.makeText(myView.context,customMsg,Toast.LENGTH_SHORT).show()
    }

    private val cal = Calendar.getInstance()
    private lateinit var myView: View
    lateinit var args: AdminAddScheduleFragmentArgs
    lateinit var userId:String
    lateinit var scheduleViewModel:SchedulesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = AdminAddScheduleFragmentArgs.fromBundle(arguments!!)
        userId = args.userId
        scheduleViewModel = ViewModelProviders.of(this).get(SchedulesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        myView =  inflater.inflate(R.layout.fragment_admin_add_schedule, container, false)
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }
/*        if(userId!="0")
            myView.et_add_day.text = userId*/
        myView.bt_add_day.setOnClickListener {
            DatePickerDialog(it.context, dateSetListener,
                    cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }
        myView.bt_add_schedule.setOnClickListener {
           if(isConnected(myView.context))
               scheduleViewModel.saveSchedule(userId,myView.et_add_day.text.toString(),this)
        }
        return myView
    }

    private fun updateDateInView() {
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        myView.et_add_day.text = sdf.format(cal.time)
    }
}

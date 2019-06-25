package com.pdm.ownyourvet.Fragments.ClientFragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdm.ownyourvet.Adapters.VaccineAdapter

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.ViewModels.VaccineViewModel
import kotlinx.android.synthetic.main.fragment_vaccine_plan.view.*

class VaccinePlanFragment : Fragment() {

    lateinit var vaccineViewModel: VaccineViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_vaccine_plan, container, false)
        init(view)
        return view
    }

    fun init(view: View){
        vaccineViewModel = ViewModelProviders.of(this).get(VaccineViewModel::class.java)

        var specie:Long
        arguments?.let {
            val safeArgs = VaccinePlanFragmentArgs.fromBundle(it)
            specie = safeArgs.specieId
        }


        var adapter =  object : VaccineAdapter(view.context){}
        val recyclerView = view.recyclerView_vaccine_plan
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        vaccineViewModel.all.observe(this, Observer { vaccinations ->
            vaccinations?.let { adapter.setVaccinations(it) }
        })

    }


}

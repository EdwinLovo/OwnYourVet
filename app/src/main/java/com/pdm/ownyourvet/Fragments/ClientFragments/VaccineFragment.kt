package com.pdm.ownyourvet.Fragments.ClientFragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.ViewModels.DiseasesViewModel
import com.pdm.ownyourvet.ViewModels.VaccineViewModel
import com.pdm.ownyourvet.isConnected
import kotlinx.android.synthetic.main.fragment_vaccine.*
import kotlinx.android.synthetic.main.fragment_vaccine.view.*

class VaccineFragment : Fragment() {

    private lateinit var diseasesViewModel: DiseasesViewModel
    private lateinit var vaccineViewModel: VaccineViewModel
    private lateinit var spinner: Spinner
    private var specie:Long = 0
    private var specieName:String = ""
    private val spinnerOptions = arrayListOf<String>()
    val spinnerOptionsId = arrayListOf<Long>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_vaccine, container, false)
        init(view)
        return view
    }

    fun init(view: View){
        diseasesViewModel = ViewModelProviders.of(this).get(DiseasesViewModel::class.java)
        vaccineViewModel = ViewModelProviders.of(this).get(VaccineViewModel::class.java)

        if (isConnected(view.context)){
            vaccineViewModel.retrieveVaccinations()
        }

        spinner = view.spinner_vaccine
        diseasesViewModel.getAllSpecies().observe(this, Observer {
            if (it.isNotEmpty()){
                spinnerOptionsId.clear()
                spinnerOptions.clear()
                it.forEach { specie ->
                    spinnerOptions.add(specie.name)
                    spinnerOptionsId.add(specie.id)
                }
                spinner.adapter = ArrayAdapter(view.context, R.layout.custom_spinner, spinnerOptions) as SpinnerAdapter
            }
        })

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view2: View?, position: Int, id: Long) {
                specie = spinnerOptionsId[position]
                specieName = spinnerOptions[position]
            }
        }

        view.button_see_plan.setOnClickListener {
            val next = VaccineFragmentDirections.nextAction(specie,specieName)
            Navigation.findNavController(it).navigate(next)
        }
    }

}

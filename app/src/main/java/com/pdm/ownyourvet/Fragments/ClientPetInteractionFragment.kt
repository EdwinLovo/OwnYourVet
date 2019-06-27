package com.pdm.ownyourvet.Fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.ViewModels.DiseasesViewModel
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pdm.ownyourvet.isConnected
import kotlinx.android.synthetic.main.fragment_client_pet_interaction.*
import kotlinx.android.synthetic.main.fragment_client_pet_interaction.view.*
import kotlinx.android.synthetic.main.fragment_client_pet_interaction.view.sp_client_pet_specie


class ClientPetInteractionFragment : Fragment() {
    lateinit var args: ClientPetInteractionFragmentArgs
    lateinit var diseasesViewModel: DiseasesViewModel

    val specieSpinnerOption = arrayListOf<String>()
    val specieSpinnerOptionsId = arrayListOf<Long>()

    val raceSpinnerOption = arrayListOf<String>()
    val raceSpinnerOptionsId = arrayListOf<Long>()

    lateinit var spClientSpecies:Spinner
    lateinit var spClientRaces:Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        diseasesViewModel = ViewModelProviders.of(this).get(DiseasesViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_client_pet_interaction, container, false)
        if (isConnected(view.context))
            diseasesViewModel.retrieveSpecies()

        spClientSpecies = view.findViewById(R.id.sp_client_pet_specie)
        spClientRaces = view.findViewById(R.id.sp_client_pet_race)
        diseasesViewModel.speciesLiveData.observe(this, Observer { list ->
            specieSpinnerOptionsId.clear()
            specieSpinnerOption.clear()
            list.forEach {

                specieSpinnerOption.add(it.name)
                specieSpinnerOptionsId.add(it.id)


            }
            spClientSpecies.adapter = ArrayAdapter(view.context,R.layout.custom_spinner,specieSpinnerOption)
        })
        diseasesViewModel.racesLiveData.observe(this, Observer {
            raceSpinnerOption.clear()
            raceSpinnerOptionsId.clear()
            it.forEach { race->
                raceSpinnerOption.add(race.name)
                raceSpinnerOptionsId.add(race.id)
            }
            spClientRaces.adapter = ArrayAdapter(view.context,R.layout.custom_spinner,raceSpinnerOption)
        })

        spClientSpecies.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                if(isConnected(p1!!.context)){
                    diseasesViewModel.getRacesOfSpecie(specieSpinnerOptionsId[p2].toString())
                }
            }

        }
        return view
    }


}

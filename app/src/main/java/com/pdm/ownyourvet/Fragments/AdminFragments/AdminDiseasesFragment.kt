package com.pdm.ownyourvet.Fragments.AdminFragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pdm.ownyourvet.Adapters.diseases.DiseaseAdapterNoPaging

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Room.Entities.Diseases
import com.pdm.ownyourvet.ViewModels.DiseasesViewModel
import com.pdm.ownyourvet.isConnected
import kotlinx.android.synthetic.main.fragment_admin_diseases.view.*


class AdminDiseasesFragment : Fragment() {

    lateinit var diseaseAdapterNoPaging: DiseaseAdapterNoPaging
    lateinit var floatingActionButton:FloatingActionButton
    lateinit var viewModel:DiseasesViewModel
    lateinit var  myView:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DiseasesViewModel::class.java)


        viewModel.getDiseasesNoPaging().observe(this, Observer {
            if(it!=null){
                diseaseAdapterNoPaging.updateList(it)

            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_admin_diseases, container, false)

        diseaseAdapterNoPaging = object :DiseaseAdapterNoPaging(){
            override fun setClickListener(itemView: View, disease: Diseases) {
                val nextAction = AdminDiseasesFragmentDirections.nextAction()
                nextAction.diseaseId = disease.id.toString()
                Navigation.findNavController(itemView).navigate(nextAction)
            }

        }
        floatingActionButton =  myView.floating_action_button

        floatingActionButton.setOnClickListener {
            val nextAction  = AdminDiseasesFragmentDirections.nextAction()
            Navigation.findNavController(it).navigate(nextAction)
        }
        myView.rv_admin_diseases.apply {
            setHasFixedSize(true)
            adapter = diseaseAdapterNoPaging
            layoutManager = LinearLayoutManager(myView.context)
        }


        if(isConnected(myView.context)){
            viewModel.retreiveDiseases()
        }


        return myView
    }





}

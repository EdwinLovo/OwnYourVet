package com.pdm.ownyourvet.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdm.ownyourvet.Adapters.DiseaseAdapter

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.ViewModels.DiseasesViewModel
import com.pdm.ownyourvet.isConnected
import kotlinx.android.synthetic.main.fragment_diseases.view.*

class DiseasesFragment : Fragment() {

    private lateinit var diseasesViewModel:DiseasesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_diseases, container, false)
        init(view)
        return view
    }

    fun init(view: View){
        diseasesViewModel = ViewModelProviders.of(this).get(DiseasesViewModel::class.java)

        var adapter = DiseaseAdapter(view.context)
        val recyclerView = view.recyclerViewDiseases
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        if (isConnected(view.context)){
            diseasesViewModel.retrieveMovies()
        }

        diseasesViewModel.allDiseases.observe(this, Observer { diseases ->
            diseases?.let { adapter.setDiseases(it) }
        })
    }

}

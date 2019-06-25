package com.pdm.ownyourvet.Fragments.AdminFragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Utils.FragmentHelper
import com.pdm.ownyourvet.ViewModels.DiseasesViewModel
import com.pdm.ownyourvet.isConnected
import kotlinx.android.synthetic.main.fragment_admin_add_diseases.view.*


class AdminAddDiseasesFragment : Fragment(),FragmentHelper {
    lateinit var args: AdminAddDiseasesFragmentArgs
    lateinit var viewModel: DiseasesViewModel
    lateinit var tvName: EditText
    lateinit var tvInfo: EditText
    lateinit var spAddDiseases: Spinner
    lateinit var btAddDiseases: Button

    val spinnerOptions = arrayListOf<String>()
    val spinnerOptionsId = arrayListOf<Long>()
    var specieId: Long = 0
    lateinit var diseaseId: String
    lateinit var myView:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        myView= inflater.inflate(R.layout.fragment_admin_add_diseases, container, false)
        args = AdminAddDiseasesFragmentArgs.fromBundle(arguments!!)
        diseaseId = args.diseaseId
        spAddDiseases = myView.sp_add_disease
        btAddDiseases = myView.bt_add_disease
        tvName = myView.et_add_disease_name
        tvInfo = myView.et_add_disease_info
        viewModel = ViewModelProviders.of(this).get(DiseasesViewModel::class.java)
        if (diseaseId != "0") btAddDiseases.text = "Modificar"
        if(isConnected(myView.context))  viewModel.retrieveSpecies()

        viewModel.getAllSpecies().observe(this, Observer {
            if (it.isNotEmpty()) {
                spinnerOptionsId.clear()
                spinnerOptions.clear()
                it.forEach { specie ->
                    spinnerOptions.add(specie.name)
                    spinnerOptionsId.add(specie.id)
                }
                if (diseaseId != "0") {
                    viewModel.getDiseaseById(diseaseId.toLong()).observe(this, Observer { disease ->
                        if(disease!=null){
                            tvName.setText(disease.name)
                            tvInfo.setText(disease.information)
                            val position = spinnerOptionsId.indexOf(disease.specie_id)
                            spAddDiseases.post { spAddDiseases.setSelection(position) }
                        }
                    })
                }

                spAddDiseases.adapter = ArrayAdapter(myView.context, R.layout.custom_spinner, spinnerOptions) as SpinnerAdapter
            }
        })
        spAddDiseases.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view2: View?, position: Int, id: Long) {
                specieId = spinnerOptionsId[position]
            }
        }
        btAddDiseases.setOnClickListener { view ->
            val name: String = tvName.text.toString()
            val info: String = tvInfo.text.toString()
            if (diseaseId == "0") viewModel.saveDisease(name, info, specieId.toString(),this)
            else viewModel.updateDisease(diseaseId,name, info, specieId.toString(),this)
            /*Navigation.findNavController(view).popBackStack()*/

        }
        return myView
    }
    override fun executeAfter() {
        val nextAction = AdminAddDiseasesFragmentDirections.nextAction()
        Navigation.findNavController(myView).navigate(nextAction)
    }



}

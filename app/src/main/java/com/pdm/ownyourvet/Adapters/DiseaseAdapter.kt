package com.pdm.ownyourvet.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Room.Entities.Diseases
import com.pdm.ownyourvet.Utils.ActivityHelper
import kotlinx.android.synthetic.main.diseases_item.view.*

abstract class DiseaseAdapter internal constructor(val activityHelper: ActivityHelper):RecyclerView.Adapter<DiseaseAdapter.DiseasesViewHolder>(){

    private var diseases = emptyList<Diseases>()

    abstract fun setClickListenerToDisease(holder: DiseasesViewHolder, disease:Diseases)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseasesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.diseases_item, parent, false)
        return DiseasesViewHolder(itemView)
    }

    override fun getItemCount() = diseases.size

    override fun onBindViewHolder(holder: DiseasesViewHolder, position: Int) = holder.bind(diseases[position])


    inner class DiseasesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val name = itemView.textView_disease
        val specie = itemView.textView_specie
        val linearLayout_disease = itemView.linearLayout_disease

        fun bind(disease:Diseases) = with(itemView){
            setClickListenerToDisease(this@DiseasesViewHolder,disease)
            name.text = disease.name
            activityHelper.setSpecieOnListItem(specie,disease.specie_id)
        }
    }

    internal fun setDiseases(diseases:List<Diseases>){
        this.diseases = diseases
        notifyDataSetChanged()
    }
}
package com.pdm.ownyourvet.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Room.Entities.Diseases
import kotlinx.android.synthetic.main.diseases_item.view.*

class DiseaseAdapter internal constructor(private val context: Context):RecyclerView.Adapter<DiseaseAdapter.DiseasesViewHolder>(){

    private var inflater = LayoutInflater.from(context)
    private var diseases = emptyList<Diseases>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseasesViewHolder {
        val itemView = inflater.inflate(R.layout.diseases_item, parent, false)
        return DiseasesViewHolder(itemView)
    }

    override fun getItemCount() = diseases.size

    override fun onBindViewHolder(holder: DiseasesViewHolder, position: Int) {
        val currentDisease = diseases[position]

        holder.name.text = currentDisease.name
        if (currentDisease.specie_id.toInt()==12){
            holder.specie.text = "Perros"
        } else {
            holder.specie.text = "Gatos"
        }
    }

    inner class DiseasesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val name = itemView.textView_disease
        val specie = itemView.textView_specie
        val linearLayout_disease = itemView.linearLayout_disease
    }

    internal fun setDiseases(diseases:List<Diseases>){
        this.diseases = diseases
        notifyDataSetChanged()
    }
}
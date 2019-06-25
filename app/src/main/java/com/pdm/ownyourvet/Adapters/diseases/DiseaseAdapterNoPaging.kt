package com.pdm.ownyourvet.Adapters.diseases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Room.Entities.Diseases
import kotlinx.android.synthetic.main.diseases_item.view.*

abstract class DiseaseAdapterNoPaging : RecyclerView.Adapter<DiseaseAdapterNoPaging.ViewHolder>() {

    var diseasesList:List<Diseases> = emptyList()

    abstract fun setClickListener(itemView: View, disease: Diseases)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseAdapterNoPaging.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.diseases_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = diseasesList.size

    override fun onBindViewHolder(holder: DiseaseAdapterNoPaging.ViewHolder, position: Int)  = holder.bind(diseasesList[position])


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.textView_disease
        fun bind(disease:Diseases) = with(itemView){
            this.setOnClickListener { setClickListener(itemView,disease) }
            name.text = disease.name
//            Log.d("CUSTOM",itemInfo.toString())
//            activityHelper.setSpecieOnListItem(specie,disease.specie_id)
        }
    }

    fun updateList(list:List<Diseases>) {
        this.diseasesList = list
        notifyDataSetChanged()
    }

}
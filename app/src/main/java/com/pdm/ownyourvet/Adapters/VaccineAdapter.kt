package com.pdm.ownyourvet.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Room.Entities.Vaccine
import kotlinx.android.synthetic.main.vaccine_item.view.*

abstract class VaccineAdapter internal constructor(context: Context):RecyclerView.Adapter<VaccineAdapter.VaccineViewHolder>(){

    private val inflater = LayoutInflater.from(context)
    private var vaccinations = emptyList<Vaccine>()

    override fun getItemCount() = vaccinations.size

    override fun onBindViewHolder(holder: VaccineViewHolder, position: Int) {
        val currentVaccine = vaccinations[position]

        holder.date.text = currentVaccine.estimated_date
        holder.information.text = currentVaccine.information
        holder.vaccine.text = currentVaccine.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccineViewHolder {
        val itemView = inflater.inflate(R.layout.vaccine_item, parent, false)
        return VaccineViewHolder(itemView)
    }

    inner class VaccineViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val vaccine:TextView = itemView.textView_vaccine
        val date:TextView = itemView.textView_estimated_date
        val information:TextView = itemView.textView_information
    }

    internal fun setVaccinations(vaccine: List<Vaccine>){
        this.vaccinations = vaccine
        notifyDataSetChanged()
    }

}
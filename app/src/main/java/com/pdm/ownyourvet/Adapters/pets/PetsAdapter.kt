package com.pdm.ownyourvet.Adapters.pets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pdm.ownyourvet.Network.Models.pets.Patient
import com.pdm.ownyourvet.Network.Models.pets.Pet
import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Room.Entities.User
import kotlinx.android.synthetic.main.user_item.view.*

abstract class PetsAdapter: RecyclerView.Adapter<PetsAdapter.ViewHolder>()  {
    var petsList:List<Patient> = emptyList()

    abstract fun setClickListener(itemView: View, patient: Patient)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int  = petsList.size

    override fun onBindViewHolder(holder: PetsAdapter.ViewHolder, position: Int)  = holder.bind(petsList[position])


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.tv_user_name
        val another = itemView.tv_user_email
        fun bind(patient: Patient) = with(itemView){
            this.setOnClickListener { setClickListener(itemView,patient) }
            name.text = patient.patient.name
            another.text = patient.patient.race.name
        }
    }
    fun updateList(list:List<Patient>) {
        this.petsList = list
        notifyDataSetChanged()
    }
}

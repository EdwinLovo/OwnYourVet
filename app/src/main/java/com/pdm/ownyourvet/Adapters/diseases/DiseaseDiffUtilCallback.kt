package com.pdm.ownyourvet.Adapters.diseases

import androidx.recyclerview.widget.DiffUtil
import com.pdm.ownyourvet.Room.Entities.Diseases

class DiseaseDiffUtilCallback : DiffUtil.ItemCallback<Diseases>() {
    override fun areItemsTheSame(oldItem: Diseases, newItem: Diseases): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Diseases, newItem: Diseases): Boolean =
        oldItem.information == newItem.information
                && oldItem.name == newItem.name
                && oldItem.specie_id == newItem.specie_id
}
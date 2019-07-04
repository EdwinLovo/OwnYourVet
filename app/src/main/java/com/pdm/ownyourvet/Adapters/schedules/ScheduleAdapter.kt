package com.pdm.ownyourvet.Adapters.schedules

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pdm.ownyourvet.Network.Models.schedules.Schedule
import com.pdm.ownyourvet.R
import kotlinx.android.synthetic.main.schedule_item.view.*

abstract class ScheduleAdapter: RecyclerView.Adapter<ScheduleAdapter.ViewHolder>()  {
    var scheduleList:List<Schedule> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = scheduleList.size

    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolder, position: Int) = holder.bind(scheduleList[position])
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val email = itemView.tv_schedule_user_email
        val day = itemView.tv_schedule_day
        fun bind(schedule: Schedule) = with(itemView){
            this.setOnClickListener { setClickListener(itemView,schedule) }
            email.text = schedule.user.email
            day.text = schedule.day
        }
    }
    fun updateList(list:List<Schedule>) {
        this.scheduleList = list
        notifyDataSetChanged()
    }
    abstract fun setClickListener(itemView: View, patient: Schedule)


}
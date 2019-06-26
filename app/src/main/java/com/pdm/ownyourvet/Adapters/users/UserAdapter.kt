package com.pdm.ownyourvet.Adapters.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Room.Entities.User
import kotlinx.android.synthetic.main.user_item.view.*

abstract class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var userList:List<User> = emptyList()
    abstract fun setClickListener(itemView: View, user: User)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = userList.size

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int)  = holder.bind(userList[position])


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.tv_user_name
        val email = itemView.tv_user_email

        fun bind(user:User) = with(itemView){
            this.setOnClickListener { setClickListener(itemView,user) }
            name.text = user.names
            email.text = user.email
        }
    }

    fun updateList(list:List<User>) {
        this.userList = list
        notifyDataSetChanged()
    }
}
package com.pdm.ownyourvet.Adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pdm.ownyourvet.Models.Message
import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Utils.CircleTransform
import com.pdm.ownyourvet.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_chat_item_left.view.*
import kotlinx.android.synthetic.main.fragment_chat_item_right.view.*
import java.text.SimpleDateFormat

class ChatAdapter(val items:List<Message>, val userId:String):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val GLOBAL_MESSAGE = 1
    private val MY_MESSAGE = 2

    private val layoutRight = R.layout.fragment_chat_item_right
    private val layoutLeft = R.layout.fragment_chat_item_left

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            MY_MESSAGE -> ViewHolderR(parent.inflate(layoutRight))
            else -> ViewHolderL(parent.inflate(layoutLeft))
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            MY_MESSAGE -> (holder as ViewHolderR).bind(items[position])
            GLOBAL_MESSAGE -> (holder as ViewHolderL).bind(items[position])
        }
    }

    override fun getItemViewType(position: Int) = if (items[position].authorId==userId) MY_MESSAGE else GLOBAL_MESSAGE

    class ViewHolderR(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(message: Message) = with(itemView){
            textViewMessageRight.text = message.message
            textViewTimeRight.text = SimpleDateFormat("hh:mm").format(message.sentAt)
            if (message.profileImageURL.isEmpty()){
                Picasso.get().load(R.drawable.user)
                        .resize(100,100)
                        .centerCrop()
                        .transform(CircleTransform())
                        .into(imageViewProfileRight)
            } else {
                Picasso.get().load(message.profileImageURL)
                        .resize(100,100)
                        .centerCrop()
                        .transform(CircleTransform())
                        .into(imageViewProfileRight)
            }

        }
    }

    class ViewHolderL(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(message: Message) = with(itemView){
            textViewMessageLeft.text = message.message
            textViewTimeLeft.text = SimpleDateFormat("hh:mm").format(message.sentAt)
            if (message.profileImageURL.isEmpty()){
                Picasso.get().load(R.drawable.user)
                        .resize(100,100)
                        .centerCrop()
                        .transform(CircleTransform())
                        .into(imageViewProfileLeft)
            } else {
                Picasso.get().load(message.profileImageURL)
                        .resize(100,100)
                        .centerCrop()
                        .transform(CircleTransform())
                        .into(imageViewProfileLeft)
            }
        }
    }

}
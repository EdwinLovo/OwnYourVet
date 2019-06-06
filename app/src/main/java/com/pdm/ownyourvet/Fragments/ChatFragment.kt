package com.pdm.ownyourvet.Fragments


import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.pdm.ownyourvet.Adapters.ChatAdapter
import com.pdm.ownyourvet.Models.Message

import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.toast
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_chat.view.*
import java.util.*
import kotlin.collections.HashMap

class ChatFragment : Fragment() {

    private lateinit var _view: View

    private val store:FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var chatDBRef:CollectionReference

    private val mAuth:FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var currentUser:FirebaseUser

    private lateinit var adapter:ChatAdapter
    private var messageList:ArrayList<Message> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _view = inflater.inflate(R.layout.fragment_chat, container, false)

        setUpChatDB()
        setUpCurrentUser()
        setUpRecyclerView()
        setUpChatBtn()

        return _view
    }

    private fun setUpChatDB(){
        chatDBRef = store.collection("chat")
    }

    private fun setUpCurrentUser(){
        currentUser = mAuth.currentUser!!
    }

    private fun setUpRecyclerView(){
        val layoutManager = LinearLayoutManager(context)
        adapter = ChatAdapter(messageList,currentUser.uid)

        _view.recyclerviewChat.setHasFixedSize(true)
        _view.recyclerviewChat.layoutManager = layoutManager
        _view.recyclerviewChat.itemAnimator = DefaultItemAnimator()
        _view.recyclerviewChat.adapter = adapter
    }

    private fun setUpChatBtn(){
        _view.buttonSend.setOnClickListener {
            val messageText = _view.editTextMessage.text.toString()
            if (messageText.isNotEmpty()){
                val message = Message(currentUser.uid, messageText, currentUser.photoUrl.toString(), Date())
                saveMessage(message)
                _view.editTextMessage.setText("")
            }
        }
    }

    private fun saveMessage(message: Message){
        val newMessage = HashMap<String,Any>()
        newMessage["authorId"] = message.authorId
        newMessage["message"] = message.message
        newMessage["profileImageURL"] = message.profileImageURL
        newMessage["sentAt"] = message.sentAt

        chatDBRef.add(newMessage)
            .addOnCompleteListener {
            activity!!.toast("Message added!")
            }
            .addOnFailureListener {
                activity!!.toast("Message error, try again!")
            }
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }


}

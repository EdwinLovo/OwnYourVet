package com.pdm.ownyourvet.Utils

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdm.ownyourvet.Room.RoomDB
import com.pdm.ownyourvet.ViewModels.DiseasesViewModel

interface ActivityHelper {

    fun getLinearLayoutManagerWithContext():LinearLayoutManager
    fun setSpecieOnListItem(textView: TextView,id:Long)
    fun getDbFromMain():RoomDB
    fun showToast(msg:String)
    fun getContext():Context


}
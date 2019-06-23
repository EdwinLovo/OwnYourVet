package com.pdm.ownyourvet.Utils

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.pdm.ownyourvet.ViewModels.DiseasesViewModel

interface ActivityHelper {

    fun getLinearLayoutManagerWithContext():LinearLayoutManager
    fun setSpecieOnListItem(textView: TextView,id:Long)
}
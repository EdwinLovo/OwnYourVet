package com.pdm.ownyourvet.Utils

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager

interface ActivityHelper {

    fun getLinearLayoutManagerWithContext():LinearLayoutManager
    fun listenDisease(id:Long,tv:TextView)
}
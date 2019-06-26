package com.pdm.ownyourvet.Activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Utils.NavigationHelper

class AdminClientInfo : AppCompatActivity(),NavigationHelper {


    lateinit var args: AdminClientInfoArgs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_client_info)
        args = AdminClientInfoArgs.fromBundle(this.intent.extras!!)
    }

    override fun getUserId(): String  = args.clientId

}

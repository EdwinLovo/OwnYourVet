package com.pdm.ownyourvet.Activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.firebase.auth.FirebaseAuth
import com.pdm.ownyourvet.R
import com.pdm.ownyourvet.Utils.NavigationHelper
import com.pdm.ownyourvet.goToActivity
import kotlinx.android.synthetic.main.activity_admin_client_info.*
import kotlinx.android.synthetic.main.activity_admin_main.*

class AdminClientInfo : AppCompatActivity(), NavigationHelper {


    lateinit var args: AdminClientInfoArgs
    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_client_info)
        setSupportActionBar(toolbar_admin)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        setUpBottomNavMenu(navController)
        setUpSideNavigationMenu(navController)
        args = AdminClientInfoArgs.fromBundle(this.intent.extras!!)
    }

    override fun getUserId(): String = args.clientId
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.exit -> {
                mAuth.signOut()
                goToActivity<LoginActivity>()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment_admin), drawer_layout_admin)
    }

    private fun setUpBottomNavMenu(navController: NavController) {
        bottom_nav?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }

    private fun setUpSideNavigationMenu(navController: NavController) {
        nav_view_admin?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }

    /*private fun setUpActionBar(navController: NavController) {
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout_admin)
    }*/
}

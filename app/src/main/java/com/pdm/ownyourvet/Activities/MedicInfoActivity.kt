package com.pdm.ownyourvet.Activities

import android.os.Bundle
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
import kotlinx.android.synthetic.main.activity_admin_main.*
import kotlinx.android.synthetic.main.activity_medic_info.*

class MedicInfoActivity : AppCompatActivity(),NavigationHelper {


    private val mAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    lateinit var args:MedicInfoActivityArgs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medic_info)
        setSupportActionBar(toolbar_admin)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        setUpBottomNavMenu(navController)
        setUpSideNavigationMenu(navController)
        args = MedicInfoActivityArgs.fromBundle(this.intent.extras!!)

    }
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
        medic_bottom_nav?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }

    private fun setUpSideNavigationMenu(navController: NavController) {
        nav_view_admin?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }

    override fun getUserId(): String  = args.medicId
}


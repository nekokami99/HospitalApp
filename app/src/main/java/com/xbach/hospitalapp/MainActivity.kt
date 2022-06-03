package com.xbach.hospitalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.xbach.hospitalapp.Fragments.*

class MainActivity : AppCompatActivity() {
    private lateinit var toogle:ActionBarDrawerToggle
    private lateinit var drawerLayout:DrawerLayout
    private lateinit var navView:NavigationView
    private lateinit var bottomNavView:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val notiFragment = NotiFragment()
        val profileFragment = ProfileFragment()
        val receiptFragment = RecieptFragment()
        val homeFragment = HomeFragment()
        val addFragment = AddFragment()
        val scheduleFragment = ScheduleFragment()

        initUI()

        toogle= ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_notification -> changeFragment(notiFragment, drawerLayout)
                R.id.nav_receipt -> changeFragment(receiptFragment, drawerLayout)
                R.id.nav_profile -> changeFragment(profileFragment, drawerLayout)
                R.id.nav_logout -> Toast.makeText(applicationContext,"clicked logout",Toast.LENGTH_SHORT).show()
            }
            true
        }

        makeCurrentFragment(homeFragment)

        bottomNavView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.bottom_nav_home -> makeCurrentFragment(homeFragment)
                R.id.bottom_nav_add -> makeCurrentFragment(addFragment)
                R.id.bottom_nav_schedule -> makeCurrentFragment(scheduleFragment)
            }
            true
        }
    }

    fun changeFragment(fragment: Fragment, drawerLayout: DrawerLayout){
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        makeCurrentFragment(fragment)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toogle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }

    private fun initUI(){
        drawerLayout=findViewById(R.id.drawer_layout)
        navView=findViewById(R.id.nav_view)
        bottomNavView=findViewById(R.id.bottom_nav_view)
    }
}
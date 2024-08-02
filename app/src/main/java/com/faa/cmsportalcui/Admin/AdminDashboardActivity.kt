package com.faa.cmsportalcui.Admin

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.faa.cmsportalcui.Authentication.WelcomeActivity
import com.faa.cmsportalcui.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class AdminDashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var adduserBtn: Button
    private lateinit var assignTaskBtn: Button
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        adduserBtn = findViewById(R.id.btn_add_staff)
        adduserBtn.setOnClickListener {
            startActivity(Intent(this@AdminDashboardActivity, StaffActivity::class.java))
        }

        assignTaskBtn = findViewById(R.id.btn_assign_task)
        assignTaskBtn.setOnClickListener {
            startActivity(Intent(this@AdminDashboardActivity, MaintananceActivity::class.java))
        }

        findViewById<LinearLayout>(R.id.totaluser).setOnClickListener {
            startActivity(Intent(this@AdminDashboardActivity, UserManagementActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.pendingrequest).setOnClickListener {
            startActivity(Intent(this@AdminDashboardActivity, MaintananceActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.completetask).setOnClickListener {
            startActivity(Intent(this@AdminDashboardActivity, CompleteTaskActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.activeworker).setOnClickListener {
            startActivity(Intent(this@AdminDashboardActivity, StaffListActivity::class.java))
        }

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener(this)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        val menuButton: ImageButton = findViewById(R.id.menu_button)
        menuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                startActivity(Intent(this, WorkerProfileActivity::class.java))
            }
            R.id.nav_notifications -> {
                startActivity(Intent(this, NotificationActivity::class.java))
            }
            R.id.nav_feedback -> {
                startActivity(Intent(this, FeedbackActivity::class.java))
            }
            R.id.nav_signout -> {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finishAffinity()
            }
            R.id.home -> {
                startActivity(Intent(this, AdminDashboardActivity::class.java))
            }
            R.id.user -> {
                startActivity(Intent(this, UserManagementActivity::class.java))
            }
            R.id.staff -> {
                startActivity(Intent(this, StaffActivity::class.java))
            }
            R.id.settings -> {
                startActivity(Intent(this, SettingActivity::class.java))
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}

package com.faa.cmsportalcui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AdminDashboardActivity : AppCompatActivity() {
    private lateinit var adduserBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)
        adduserBtn = findViewById(R.id.btn_add_user)

        adduserBtn.setOnClickListener {
            startActivity(Intent(this@AdminDashboardActivity, UserMAnagementActivity::class.java))
        }

    }
}
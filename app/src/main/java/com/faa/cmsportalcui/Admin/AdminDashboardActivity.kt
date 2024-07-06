package com.faa.cmsportalcui.Admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R

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
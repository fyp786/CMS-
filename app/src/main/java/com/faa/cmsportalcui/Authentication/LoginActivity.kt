package com.faa.cmsportalcui.Authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.Admin.AdminDashboardActivity
import com.faa.cmsportalcui.R

class LoginActivity : AppCompatActivity() {
    private lateinit var googleBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        googleBtn = findViewById(R.id.btn_google)
        googleBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, AdminDashboardActivity::class.java))
        }

    }
}
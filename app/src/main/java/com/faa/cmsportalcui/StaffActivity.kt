package com.faa.cmsportalcui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StaffActivity : AppCompatActivity() {
    private lateinit var textBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff)
        textBtn = findViewById(R.id.tvTitle)

        textBtn.setOnClickListener {
            startActivity(Intent(this@StaffActivity, ReportActivity::class.java))
        }
    }
}
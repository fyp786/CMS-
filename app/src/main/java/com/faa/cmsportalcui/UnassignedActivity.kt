package com.faa.cmsportalcui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UnassignedActivity : AppCompatActivity() {
    private lateinit var textBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unassigned)
        textBtn = findViewById(R.id.textView2)

        textBtn.setOnClickListener {
            startActivity(Intent(this@UnassignedActivity, StaffActivity::class.java))
        }
    }
}
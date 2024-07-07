package com.faa.cmsportalcui.Admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.faa.cmsportalcui.R

class StaffProfileActivity : AppCompatActivity() {
    private lateinit var editBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_profile)
        editBtn = findViewById(R.id.edit_button)

        editBtn.setOnClickListener {
            startActivity(Intent(this@StaffProfileActivity, FeedbackActivity::class.java))
        }

    }
}
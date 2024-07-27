package com.faa.cmsportalcui.Admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.faa.cmsportalcui.R

class StaffListActivity : AppCompatActivity() {
    private lateinit var addstaffBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_list)
        addstaffBtn = findViewById(R.id.allRolesButton)

        addstaffBtn.setOnClickListener {
            startActivity(Intent(this@StaffListActivity, StaffProfileActivity::class.java))
        }

    }
}
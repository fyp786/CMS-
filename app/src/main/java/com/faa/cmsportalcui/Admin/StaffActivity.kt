package com.faa.cmsportalcui.Admin

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R

class StaffActivity : AppCompatActivity() {
    private lateinit var textBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff)
        textBtn = findViewById(R.id.tvTitle)

        textBtn.setOnClickListener {
            startActivity(Intent(this@StaffActivity, MaintananceStaffDetailsActivity::class.java))
        }
    }
}
package com.faa.cmsportalcui.Admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R

class ReportActivity : AppCompatActivity() {
    private lateinit var reportBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        reportBtn = findViewById(R.id.report_button)

        reportBtn.setOnClickListener {
            startActivity(Intent(this@ReportActivity, SettingActivity::class.java))
        }

    }
}
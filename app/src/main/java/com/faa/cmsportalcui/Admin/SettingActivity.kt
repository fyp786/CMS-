package com.faa.cmsportalcui.Admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R

class SettingActivity : AppCompatActivity() {
    private lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        saveBtn = findViewById(R.id.save_button)

        saveBtn.setOnClickListener {
            startActivity(Intent(this@SettingActivity, NotificationActivity::class.java))
        }

    }
}
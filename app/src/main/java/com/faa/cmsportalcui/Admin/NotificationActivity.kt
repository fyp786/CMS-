package com.faa.cmsportalcui.Admin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R

class NotificationActivity : AppCompatActivity() {
    private lateinit var notificationBtn: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        notificationBtn = findViewById(R.id.notification_button)

        notificationBtn.setOnClickListener {
            startActivity(Intent(this@NotificationActivity, StaffListActivity::class.java))
        }

    }
}
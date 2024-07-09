package com.faa.cmsportalcui.User

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R

class UserMaintanancerequestActivity : AppCompatActivity() {
    private lateinit var filterBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_maintanancerequest)
        filterBtn = findViewById(R.id.filter_by_date_button)

        filterBtn.setOnClickListener {
            startActivity(Intent(this@UserMaintanancerequestActivity, UserNotificationActivity::class.java))
        }
    }
}
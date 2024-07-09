package com.faa.cmsportalcui.User

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R

class UserNotificationActivity : AppCompatActivity() {
    private lateinit var markBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_notification)
        markBtn = findViewById(R.id.button_mark_all_read)

        markBtn.setOnClickListener {
            startActivity(Intent(this@UserNotificationActivity, UserProfileActivity::class.java))
        }

    }
}
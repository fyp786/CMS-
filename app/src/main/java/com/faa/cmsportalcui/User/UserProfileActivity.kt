package com.faa.cmsportalcui.User

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.faa.cmsportalcui.R

class UserProfileActivity : AppCompatActivity() {
    private lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        saveBtn = findViewById(R.id.button_save)

        saveBtn.setOnClickListener {
            startActivity(Intent(this@UserProfileActivity, UserSettingActivity::class.java))
        }

    }
}
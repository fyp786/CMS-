package com.faa.cmsportalcui.Authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Initialize views using findViewById
        val userButton: Button = findViewById(R.id.user_button)
        val adminButton: Button = findViewById(R.id.admin_button)

        userButton.setOnClickListener {
            val intent = Intent(this, AuthenticationActivity::class.java)
            intent.putExtra("user_type", "user")
            startActivity(intent)
        }

        adminButton.setOnClickListener {
            val intent = Intent(this, AuthenticationActivity::class.java)
            intent.putExtra("user_type", "admin")
            startActivity(intent)
        }
    }
}

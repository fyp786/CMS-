package com.faa.cmsportalcui.Authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R
import com.faa.cmsportalcui.User.UserDashboardActivity

class WelcomeActivity : AppCompatActivity() {
    var adminBtn: Button? = null
    var userBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        adminBtn = findViewById<Button>(R.id.admin_button)
        userBtn = findViewById<Button>(R.id.user_button)

        adminBtn?.setOnClickListener {
            startActivity(
                Intent(
                    this@WelcomeActivity,
                    AuthenticationActivity::class.java
                )
            )
        }
        userBtn?.setOnClickListener {
            startActivity(
                Intent(
                    this@WelcomeActivity,
                    UserDashboardActivity::class.java
                )
            )
        }
    }
}

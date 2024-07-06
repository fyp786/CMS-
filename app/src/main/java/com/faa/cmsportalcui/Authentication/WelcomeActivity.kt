package com.faa.cmsportalcui.Authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R

class WelcomeActivity : AppCompatActivity() {
    var userBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        userBtn = findViewById<Button>(R.id.user_button)

        userBtn?.setOnClickListener {
            startActivity(
                Intent(
                    this@WelcomeActivity,
                    AuthenticationActivity::class.java
                )
            )
        }
    }
}

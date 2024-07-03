package com.faa.cmsportalcui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

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

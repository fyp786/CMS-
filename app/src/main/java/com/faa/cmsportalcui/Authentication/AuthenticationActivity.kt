package com.faa.cmsportalcui.Authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var loginBtn: Button
    private lateinit var signupBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        loginBtn = findViewById(R.id.login_button)
        signupBtn = findViewById(R.id.signup_button)

        loginBtn.setOnClickListener {
            startActivity(Intent(this@AuthenticationActivity, LoginActivity::class.java))
        }

        signupBtn.setOnClickListener {
            startActivity(Intent(this@AuthenticationActivity, SignUpActivity::class.java))
        }
    }
}

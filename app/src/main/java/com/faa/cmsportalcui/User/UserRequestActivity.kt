package com.faa.cmsportalcui.User

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R

class UserRequestActivity : AppCompatActivity() {
    private lateinit var submitBtn: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_request)
        submitBtn = findViewById(R.id.submit_btn)

        submitBtn.setOnClickListener {
            startActivity(Intent(this@UserRequestActivity, UserMaintanancerequestActivity::class.java))
        }

    }
}
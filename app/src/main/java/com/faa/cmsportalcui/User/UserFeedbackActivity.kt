package com.faa.cmsportalcui.User

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.faa.cmsportalcui.R

class UserFeedbackActivity : AppCompatActivity() {
    private lateinit var submitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_feedback)
        submitBtn = findViewById(R.id.button_submit)

        submitBtn.setOnClickListener {
            startActivity(Intent(this@UserFeedbackActivity, UserHelpAndSupportActivity::class.java))
        }

    }
}
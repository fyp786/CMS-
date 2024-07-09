package com.faa.cmsportalcui.User

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R

class UserDashboardActivity : AppCompatActivity() {
    private lateinit var arrowBtn: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_dashboard)
        arrowBtn = findViewById(R.id.arrow)

        arrowBtn.setOnClickListener {
            startActivity(Intent(this@UserDashboardActivity, UserRequestActivity::class.java))
        }

    }
}
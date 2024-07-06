package com.faa.cmsportalcui.Admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R

class UserMAnagementActivity : AppCompatActivity() {
    private lateinit var addUserBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_management)
        addUserBtn = findViewById(R.id.btnAddUser)

        addUserBtn.setOnClickListener {
            startActivity(Intent(this@UserMAnagementActivity, MaintananceActivity::class.java))
        }

    }
}
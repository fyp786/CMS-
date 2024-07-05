package com.faa.cmsportalcui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MaintananceDetailActivity : AppCompatActivity() {
    private lateinit var assignBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maintanance_detail)
        assignBtn = findViewById(R.id.btnAssign)

        assignBtn.setOnClickListener {
            startActivity(Intent(this@MaintananceDetailActivity, AssignWorkerActivity::class.java))
        }


    }
}
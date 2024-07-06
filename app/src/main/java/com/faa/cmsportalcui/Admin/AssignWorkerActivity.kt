package com.faa.cmsportalcui.Admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R

class AssignWorkerActivity : AppCompatActivity() {
    private lateinit var assignBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign_worker)
        assignBtn = findViewById(R.id.assign_button)

        assignBtn.setOnClickListener {
            startActivity(Intent(this@AssignWorkerActivity, UnassignedActivity::class.java))
        }

    }
}
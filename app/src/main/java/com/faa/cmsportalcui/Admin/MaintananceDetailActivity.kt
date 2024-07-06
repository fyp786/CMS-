package com.faa.cmsportalcui.Admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R

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
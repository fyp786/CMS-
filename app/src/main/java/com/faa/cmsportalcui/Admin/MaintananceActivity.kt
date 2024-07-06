package com.faa.cmsportalcui.Admin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MaintananceActivity : AppCompatActivity() {
    private lateinit var addBtn: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maintanance)
        addBtn = findViewById(R.id.fab)

        addBtn.setOnClickListener {
            startActivity(Intent(this@MaintananceActivity, MaintananceDetailActivity::class.java))
        }

    }
}
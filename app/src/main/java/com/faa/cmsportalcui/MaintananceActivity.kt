package com.faa.cmsportalcui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
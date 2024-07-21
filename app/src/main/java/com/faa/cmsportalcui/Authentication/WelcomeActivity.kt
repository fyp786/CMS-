package com.faa.cmsportalcui.Authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class WelcomeActivity : AppCompatActivity() {
    private lateinit var firestore: FirebaseFirestore
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        firestore = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()

        // Initialize views using findViewById
        val userButton: Button = findViewById(R.id.user_button)
        val adminButton: Button = findViewById(R.id.admin_button)

        userButton.setOnClickListener {
            updateUserType("user")
        }

        adminButton.setOnClickListener {
            updateUserType("admin")
        }
    }
    private fun updateUserType(type: String) {
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            firestore.collection("users").document(currentUser.uid).update("userType", type)
                .addOnSuccessListener {
                    val intent = Intent(this, AuthenticationActivity::class.java)
                    intent.putExtra("user_type", type)
                    startActivity(intent)
                    finish()  // Close WelcomeActivity
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to update user type", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "Please log in first", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()  // Close WelcomeActivity
        }
    }

}

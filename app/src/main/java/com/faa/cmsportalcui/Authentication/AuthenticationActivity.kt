package com.faa.cmsportalcui.Authentication

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.Admin.AdminDashboardActivity
import com.faa.cmsportalcui.R
import com.faa.cmsportalcui.User.UserDashboardActivity
import com.faa.cmsportalcui.databinding.ActivityAuthenticationBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var progressDialog: ProgressDialog
    private lateinit var mAuth: FirebaseAuth
    private lateinit var gsc: GoogleSignInClient
    private lateinit var binding: ActivityAuthenticationBinding
    private lateinit var back_button: ImageView
    private var userType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the user type from the intent
        userType = intent.getStringExtra("user_type")

        back_button = findViewById(R.id.back_button)
        back_button.setOnClickListener {
            startActivity(Intent(this@AuthenticationActivity, WelcomeActivity::class.java))
        }

        mAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        gsc = GoogleSignIn.getClient(this, gso)

        binding.googleButton.setOnClickListener { signIn() }
        binding.loginButton.setOnClickListener { navigateToLogin() }
        binding.signupButton.setOnClickListener { navigateToSignUp() }
    }

    private fun signIn() {
        val signInIntent = gsc.signInIntent
        startActivityForResult(signInIntent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.getResult(ApiException::class.java)
                navigateToNextActivity()
            } catch (e: ApiException) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun navigateToNextActivity() {
        val intent = if (userType == "admin") {
            Intent(this, AdminDashboardActivity::class.java)
        } else if (userType == "user") {
            Intent(this, UserDashboardActivity::class.java)
        } else {
            Toast.makeText(this, "User type not recognized", Toast.LENGTH_SHORT).show()
            return
        }
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }


    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            putExtra("user_type", userType)
        }
        startActivity(intent)
    }

    private fun navigateToSignUp() {
        val intent = Intent(this, SignUpActivity::class.java).apply {
            putExtra("user_type", userType)
        }
        startActivity(intent)
    }
}

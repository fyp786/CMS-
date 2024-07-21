package com.faa.cmsportalcui.Authentication

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.faa.cmsportalcui.Admin.AdminDashboardActivity
import com.faa.cmsportalcui.R
import com.faa.cmsportalcui.User.UserDashboardActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {
    private lateinit var createNewAccount: TextView
    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var googleBtn: Button
    private lateinit var back_button: ImageView
    private lateinit var progressDialog: ProgressDialog
    private lateinit var mAuth: FirebaseAuth
    private lateinit var gsc: GoogleSignInClient
    private lateinit var firestore: FirebaseFirestore
    private var userType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userType = intent.getStringExtra("user_type")

        createNewAccount = findViewById(R.id.tv_sign_up)
        inputEmail = findViewById(R.id.et_email)
        inputPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_sign_up)
        googleBtn = findViewById(R.id.btn_google)
        back_button = findViewById(R.id.back_button)

        back_button.setOnClickListener {
            startActivity(Intent(this@LoginActivity, AuthenticationActivity::class.java))
        }

        progressDialog = ProgressDialog(this)
        mAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        gsc = GoogleSignIn.getClient(this, gso)

        createNewAccount.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java).apply {
                putExtra("user_type", userType)
            })
        }

        googleBtn.setOnClickListener {
            signIn()
        }

        btnLogin.setOnClickListener {
            performLogin()
        }
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
                verifyUserTypeAndNavigate()
            } catch (e: ApiException) {
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun performLogin() {
        val email = inputEmail.text.toString()
        val password = inputPassword.text.toString()

        if (!email.matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
            inputEmail.error = "Enter Correct Email"
        } else if (password.isEmpty() || password.length < 6) {
            inputPassword.error = "Enter Correct Password"
        } else {
            progressDialog.setMessage("Please Wait While Login...")
            progressDialog.setTitle("Login")
            progressDialog.setCanceledOnTouchOutside(false)
            progressDialog.show()

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                progressDialog.dismiss()
                if (task.isSuccessful) {
                    verifyUserTypeAndNavigate()
                } else {
                    Toast.makeText(this@LoginActivity, task.exception?.message ?: "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun verifyUserTypeAndNavigate() {
        val currentUser = mAuth.currentUser ?: return
        val userRef = firestore.collection("users").document(currentUser.uid)

        userRef.get().addOnSuccessListener { document ->
            if (document.exists()) {
                val actualUserType = document.getString("userType")

                if (actualUserType == userType) {
                    navigateToDashboard()
                } else {
                    Toast.makeText(this, "Incorrect login type", Toast.LENGTH_SHORT).show()
                    mAuth.signOut() // Log out the user
                    startActivity(Intent(this, WelcomeActivity::class.java))
                    finish()  // Close LoginActivity
                }
            } else {
                Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show()
                mAuth.signOut() // Log out the user
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()  // Close LoginActivity
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to retrieve user data", Toast.LENGTH_SHORT).show()
            mAuth.signOut() // Log out the user
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()  // Close LoginActivity
        }
    }

    private fun navigateToDashboard() {
        val intent = if (userType == "admin") {
            Intent(this@LoginActivity, AdminDashboardActivity::class.java)
        } else {
            Intent(this@LoginActivity, UserDashboardActivity::class.java)
        }

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}

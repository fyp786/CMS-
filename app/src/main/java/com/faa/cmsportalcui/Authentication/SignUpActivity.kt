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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : AppCompatActivity() {
    private lateinit var alreadyHaveAccount: TextView
    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var inputConfirmPassword: EditText
    private lateinit var inputUsername: EditText
    private lateinit var btnRegister: Button
    private lateinit var back_button: ImageView
    private lateinit var progressDialog: ProgressDialog
    private lateinit var mAuth: FirebaseAuth
    private var mUser: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        alreadyHaveAccount = findViewById(R.id.tv_sign_in)
        inputEmail = findViewById(R.id.et_email)
        inputPassword = findViewById(R.id.et_password)
        inputConfirmPassword = findViewById(R.id.et_confirm_password)
        inputUsername = findViewById(R.id.et_username)
        btnRegister = findViewById(R.id.btn_sign_up)
        back_button = findViewById(R.id.back_button) // Initialize the back_button property
        progressDialog = ProgressDialog(this)
        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth.currentUser

        back_button.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, AuthenticationActivity::class.java))
        }

        alreadyHaveAccount.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
        }

        btnRegister.setOnClickListener {
            performAuth()
        }
    }


    private fun performAuth() {
        val username = inputUsername.text.toString()
        val email = inputEmail.text.toString()
        val password = inputPassword.text.toString()
        val confirmPassword = inputConfirmPassword.text.toString()

        if (username.isEmpty()) {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
        } else if (!email.matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
            inputEmail.error = "Enter Correct Email"
        } else if (password.isEmpty() || password.length < 6) {
            inputPassword.error = "Enter Correct Password"
        } else if (password != confirmPassword) {
            inputConfirmPassword.error = "Passwords do not match"
        } else {
            progressDialog.setMessage("Please Wait While Registration...")
            progressDialog.setTitle("Registration")
            progressDialog.setCanceledOnTouchOutside(false)
            progressDialog.show()

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    progressDialog.dismiss()
                    sendUserToNextActivity()
                    Toast.makeText(this@SignUpActivity, "Registration Successful", Toast.LENGTH_SHORT).show()
                } else {
                    progressDialog.dismiss()
                    Toast.makeText(this@SignUpActivity, task.exception?.message ?: "Registration Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun sendUserToNextActivity() {
        val intent: Intent
        val email = inputEmail.text.toString()

        intent = if (email.contains("admin")) {
            Intent(this@SignUpActivity, AdminDashboardActivity::class.java)
        } else {
            Intent(this@SignUpActivity, UserDashboardActivity::class.java)
        }

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}

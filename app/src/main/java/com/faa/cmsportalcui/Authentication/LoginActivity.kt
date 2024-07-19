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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    private lateinit var createNewAccount: TextView
    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var googleBtn: Button
    private lateinit var back_button: ImageView
    private lateinit var progressDialog: ProgressDialog
    private lateinit var mAuth: FirebaseAuth
    private var mUser: FirebaseUser? = null
    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
        mUser = mAuth.currentUser

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        gsc = GoogleSignIn.getClient(this, gso)

        createNewAccount.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
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
                navigateToDashboard()
            } catch (e: ApiException) {
                Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToDashboard() {
        val intent: Intent
        val account = GoogleSignIn.getLastSignedInAccount(this)
        val email = account?.email ?: ""

        intent = if (email.contains("admin")) {
            Intent(this@LoginActivity, AdminDashboardActivity::class.java)
        } else {
            Intent(this@LoginActivity, UserDashboardActivity::class.java)
        }

        finish()
        startActivity(intent)
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

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    progressDialog.dismiss()
                    sendUserToNextActivity()
                    Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                } else {
                    progressDialog.dismiss()
                    Toast.makeText(this@LoginActivity, task.exception?.message ?: "Login Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun sendUserToNextActivity() {
        val intent: Intent
        val email = inputEmail.text.toString()

        intent = if (email.contains("admin")) {
            Intent(this@LoginActivity, AdminDashboardActivity::class.java)
        } else {
            Intent(this@LoginActivity, UserDashboardActivity::class.java)
        }

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}

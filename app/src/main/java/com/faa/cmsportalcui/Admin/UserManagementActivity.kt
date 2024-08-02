package com.faa.cmsportalcui.Admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.faa.cmsportalcui.databinding.ActivityUserManagementBinding
import com.faa.cmsportalcui.usermanagement.User
import com.faa.cmsportalcui.usermanagement.UserAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class UserManagementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserManagementBinding
    private lateinit var userAdapter: UserAdapter
    private val users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(users)
        binding.rvUsers.adapter = userAdapter


        // Fetch users
        fetchUsers()
    }

    private fun fetchUsers() {
        val db = FirebaseFirestore.getInstance()
        db.collection("users").get()
            .addOnSuccessListener { result: QuerySnapshot ->
                for (document in result) {
                    val username = document.getString("username") ?: ""
                    val userType = document.getString("userType") ?: ""
                    val profileImageUrl = "https://firebasestorage.googleapis.com/v0/b/cms-portal-cui.appspot.com/o/placeholder.png?alt=media&token=3d835e45-cb27-44bf-a024-c922c7cbc9a6"

                    users.add(User(username, userType, profileImageUrl))
                }
                userAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                // Handle error
            }
    }
}

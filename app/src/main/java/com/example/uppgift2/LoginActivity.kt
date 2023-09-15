package com.example.uppgift2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private var usernameEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var loginButton: Button? = null
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        loginButton = findViewById<Button>(R.id.loginButton)
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        loginButton?.setOnClickListener(View.OnClickListener {
            val username = usernameEditText?.text.toString()
            val password = passwordEditText?.text.toString()

            // Check if EditText views are not null before accessing their values
            if (usernameEditText != null && passwordEditText != null) {
                // Save user data in SharedPreferences
                val editor = sharedPreferences?.edit()
                editor?.putString("username", username)
                editor?.putString("password", password)
                editor?.apply()
            }

            val intent = Intent(this@LoginActivity, ProfileActivity::class.java)
            startActivity(intent)
        })
    }

    override fun onPause() {
        super.onPause()
        val username = usernameEditText?.text.toString()
        val password = passwordEditText?.text.toString()

        // Check if EditText views are not null before accessing their values
        if (usernameEditText != null && passwordEditText != null) {
            val editor = sharedPreferences?.edit()
            editor?.putString("username", username)
            editor?.putString("password", password)
            editor?.apply()
        }
    }

}
package com.example.uppgift2

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class ProfileActivity : AppCompatActivity() {
    private var ageEditText: EditText? = null
    private var hasDriverLicenseCheckBox: CheckBox? = null
    private var genderRadioGroup: RadioGroup? = null
    private var emailEditText: EditText? = null
    private var phoneEditText: EditText? = null
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        ageEditText = findViewById(R.id.ageEditText)
        hasDriverLicenseCheckBox = findViewById(R.id.hasDriverLicenseCheckBox)
        genderRadioGroup = findViewById(R.id.genderRadioGroup)
        emailEditText = findViewById(R.id.emailEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Hämtar sparade användaruppgifter från SharedPreferences
        val savedAge = sharedPreferences?.getString("age", "")
        val savedHasLicense = sharedPreferences?.getBoolean("hasLicense", false)
        val savedGenderId = sharedPreferences?.getInt("genderId", -1)
        val savedEmail = sharedPreferences?.getString("email", "")
        val savedPhone = sharedPreferences?.getString("phone", "")

        ageEditText?.setText(savedAge)
        hasDriverLicenseCheckBox?.isChecked = savedHasLicense ?: false
        emailEditText?.setText(savedEmail)
        phoneEditText?.setText(savedPhone)

        if (savedGenderId != null && savedGenderId != -1) {
            genderRadioGroup?.check(savedGenderId)
        }
    }

    override fun onPause() {
        super.onPause()

        val age = ageEditText?.text.toString()
        val hasLicense = hasDriverLicenseCheckBox?.isChecked ?: false
        val genderId = genderRadioGroup?.checkedRadioButtonId ?: -1
        val email = emailEditText?.text.toString()
        val phone = phoneEditText?.text.toString()

        val editor = sharedPreferences?.edit()
        editor?.putString("age", age)
        editor?.putBoolean("hasLicense", hasLicense)
        editor?.putInt("genderId", genderId)
        editor?.putString("email", email)
        editor?.putString("phone", phone)
        editor?.apply()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
       // Log.d("MenuDebug", "onCreateOptionsMenu called")
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.menu_Login -> {
                // Starta LoginActivity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
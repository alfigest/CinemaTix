package com.example.cinematixapps.useracc.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.example.cinematixapps.R
import com.example.cinematixapps.home.HomeActivity
import com.example.cinematixapps.useracc.register.RegisterActivity
import com.example.cinematixapps.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var iUsername :String
    lateinit var iPassword :String

    lateinit var mDatabase: DatabaseReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        preferences = Preferences(this)

        preferences.setValues("onboarding", "1") // kalau sudah sampai di login, tidak perlu OB Screen lagi

        if (preferences.getValues("status").equals("1")) {

            val intent = Intent(this@LoginActivity,
                HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finishAffinity()
        }

        mDatabase = FirebaseDatabase.getInstance().getReference("User")

        btn_home.setOnClickListener {
            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()

            if (iUsername.equals("")) {
                et_username.error = "Username kok kosong?"
                et_username.requestFocus() //fokus ke yang error
            } else if (iPassword.equals("")) {
                et_password.error = "Password kok kosong?"
                et_password.requestFocus()
            } else {
                pushLogin(iUsername, iPassword)
            }
        }

        btn_daftar.setOnClickListener {
            val intent = Intent(this@LoginActivity,
                RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(this@LoginActivity, "User Name Kamu Ngga Ketemu Nih", Toast.LENGTH_LONG).show()

                } else {
                    if (user.password.equals(iPassword)){
                        Toast.makeText(this@LoginActivity, "Haloo Selamat Datang", Toast.LENGTH_LONG).show()

                        preferences.setValues("nama", user.nama.toString())
                        preferences.setValues("user", user.username.toString())
                        preferences.setValues("url", user.url.toString())
                        preferences.setValues("email", user.email.toString())
                        preferences.setValues("saldo", user.saldo.toString())
                        preferences.setValues("status", "1")

                        val intent = Intent(this@LoginActivity,
                            HomeActivity::class.java)
                        startActivity(intent)
                        finishAffinity()

                    } else {
                        Toast.makeText(this@LoginActivity, "Maaf Password Kamu salah", Toast.LENGTH_LONG).show()
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}

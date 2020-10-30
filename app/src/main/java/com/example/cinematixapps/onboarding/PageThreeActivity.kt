package com.example.cinematixapps.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.cinematixapps.R
import com.example.cinematixapps.R.*
import com.example.cinematixapps.useracc.login.LoginActivity
import kotlinx.android.synthetic.main.activity_page_three.*

class PageThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_page_three)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        btn_home.setOnClickListener {
            val intent = Intent(this@PageThreeActivity,
                LoginActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

//        val database = FirebaseDatabase.getInstance()
//        val myRef = database.getReference("message")
//
//        myRef.setValue("Hello, World!")

    }
}

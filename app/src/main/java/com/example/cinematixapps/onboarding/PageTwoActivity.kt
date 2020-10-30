package com.example.cinematixapps.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.cinematixapps.R
import kotlinx.android.synthetic.main.activity_page_two.*

class PageTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_two)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        btn_home.setOnClickListener {
            val intent = Intent(this@PageTwoActivity,PageThreeActivity::class.java)
            startActivity(intent)
        }
    }
}
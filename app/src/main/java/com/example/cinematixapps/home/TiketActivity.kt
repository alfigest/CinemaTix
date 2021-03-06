package com.example.cinematixapps.home

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cinematixapps.R
import com.example.cinematixapps.home.model.Film
import com.example.cinetixapp.checkout.model.Checkout
import kotlinx.android.synthetic.main.activity_tiket.*

class TiketActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    internal lateinit var myDialog : Dialog
    internal lateinit var cancel_dialog : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiket)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        imageView3.setOnClickListener {
            finish()
        }

        val data = intent.getParcelableExtra<Film>("data")

        if (data != null) {
            tv_title.text = data.judul
        }
        if (data != null) {
            tv_genre.text = data.genre
        }
        if (data != null) {
            tv_rate.text = data.rating
        }

        if (data != null) {
            Glide.with(this)
                .load(data.poster)
                .into(iv_poster_image)
        }

        rc_checkout.layoutManager = LinearLayoutManager(this)
        dataList.add(Checkout("C1",""))
        dataList.add(Checkout("C2",""))

        rc_checkout.adapter = TiketAdapter(dataList) {
        }

        iv_barcode.setOnClickListener {
            ShowDialog()
        }

    }

    private fun ShowDialog() {
        myDialog = Dialog(this)
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        myDialog.setContentView(R.layout.dialog_activity)

        myDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        cancel_dialog = myDialog.findViewById<View>(R.id.close_dialog) as Button
        cancel_dialog.isEnabled = true
        cancel_dialog.setOnClickListener {
            myDialog.cancel()
        }

        myDialog.show()
    }
}

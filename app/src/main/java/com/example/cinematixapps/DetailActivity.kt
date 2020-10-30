package com.example.cinematixapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cinematixapps.checkout.PilihBangkuActivity
import com.example.cinematixapps.home.dashboard.PlaysAdapter
import com.example.cinematixapps.home.model.Film
import com.example.cinematixapps.home.model.Plays
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<Plays>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val data = intent.getParcelableExtra<Film>("data")

        if (data != null) {
            mDatabase = FirebaseDatabase.getInstance().getReference("Film")
                .child(data.judul.toString())
                .child("play")
        }

        if (data != null) {
            tv_kursi.text = data.judul
        }
        if (data != null) {
            tv_genre.text = data.genre
        }
        if (data != null) {
            tv_desc.text = data.desc
        }
        if (data != null) {
            tv_rate.text = data.rating
        }

        if (data != null) {
            Glide.with(this)
                .load(data.poster)
                .into(iv_poster)
        }

        btn_pilih_bangku.setOnClickListener {
            val intent = Intent(this@DetailActivity,
                PilihBangkuActivity::class.java).putExtra("data", data)
            startActivity(intent)
        }

        iv_back.setOnClickListener {
            finish()
        }

        rv_who_play.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.getChildren()) {

                    val film = getdataSnapshot.getValue(Plays::class.java!!)
                    dataList.add(film!!)
                }

                rv_who_play.adapter = PlaysAdapter(dataList) {

                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DetailActivity, ""+error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}

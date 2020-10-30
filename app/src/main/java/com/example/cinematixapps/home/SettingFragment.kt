package com.example.cinematixapps.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cinematixapps.R
import com.example.cinematixapps.useracc.EditProfile
import com.example.cinematixapps.utils.Preferences
import com.example.cinematixapps.wallet.MyWallet

import kotlinx.android.synthetic.main.fragment_setting.*

/**
 * A simple [Fragment] subclass.
 */
class SettingFragment : Fragment() {

    lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(context!!.applicationContext)

        iv_nama.text = preferences.getValues("nama")
        tv_email.text = preferences.getValues("email")

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(iv_profile)

        tv_edit_profile.setOnClickListener {
            val intent = Intent(context,
                EditProfile::class.java)
            startActivity(intent)
        }

        tv_my_wallet.setOnClickListener {
            val intent = Intent(context,
                MyWallet::class.java)
            startActivity(intent)
//            bisa juga :
//            startActivity(Intent(activity,MyWallet::class.java))
        }
    }

}

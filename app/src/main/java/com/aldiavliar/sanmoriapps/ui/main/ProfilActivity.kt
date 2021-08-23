package com.aldiavliar.sanmoriapps.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.sharedpref.MyPreferenceFragment


class ProfilActivity : AppCompatActivity() {

    private lateinit var back : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        back =  findViewById(R.id.onPressedBack)
        back.setOnClickListener {
            super.onBackPressed()
        }

        supportFragmentManager.beginTransaction().add(R.id.setting_holder, MyPreferenceFragment()).commit()


    }

}

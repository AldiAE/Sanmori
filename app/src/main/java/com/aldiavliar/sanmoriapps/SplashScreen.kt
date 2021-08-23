package com.aldiavliar.sanmoriapps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aldiavliar.sanmoriapps.introSlider.WelcomeActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val myThread = object:Thread() {
            public override fun run() {
                try
                {
                    Thread.sleep(3700)
                    val intent = Intent(getApplicationContext(), WelcomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                catch (e:InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        myThread.start()
    }
}
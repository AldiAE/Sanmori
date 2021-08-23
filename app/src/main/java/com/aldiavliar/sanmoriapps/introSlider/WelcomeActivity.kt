package com.aldiavliar.sanmoriapps.introSlider

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.ui.main.MainActivity
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.form_data.*


@Suppress("DEPRECATION")
class WelcomeActivity : AppCompatActivity() {
    private var valueNama: String? = null
    private lateinit var txtName: AppCompatEditText
    private lateinit var btnYes: Button
    private var viewPager: ViewPager? = null
    private var myViewPagerAdapter: MyViewPagerAdapter? = null
    private var dotsLayout: LinearLayout? = null
    private var dots: Array<TextView?> = emptyArray()
    private var layouts: IntArray? = null
    private var btnSkip: Button? = null
    private var btnNext: Button? = null
    private lateinit var prefManager: ApplicationPrefs

    companion object {
        const val ALERT_DIALOG_OPENED = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= 21) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        setContentView(R.layout.activity_welcome)
        init()
        changeStatusBarColor()
        setDots()
        setAdapter()
        setListeners()

    }

    private fun init() {
        prefManager = ApplicationPrefs()
        if (prefManager.isNotFirstTime()) {
            openMainScreen()
        }
        viewPager = findViewById(R.id.view_pager)
        dotsLayout = findViewById(R.id.layoutDots)
        btnSkip = findViewById(R.id.btn_skip)
        btnNext = findViewById(R.id.btn_next)
    }

    private var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            addBottomDots(position)
            if (position == layouts!!.size - 1) {
                btnNext!!.text = getString(R.string.start)
                btnSkip!!.visibility = View.GONE
            } else {
                btnNext!!.text = getString(R.string.next)
                btnSkip!!.visibility = View.VISIBLE
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}

        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    private fun setDots() {
        layouts =
            intArrayOf(R.layout.slide1, R.layout.sllide2, R.layout.slide3, R.layout.slide4)
        addBottomDots(0)
    }

    private fun setAdapter() {
        myViewPagerAdapter = MyViewPagerAdapter()
        viewPager!!.adapter = myViewPagerAdapter
        viewPager!!.addOnPageChangeListener(viewPagerPageChangeListener)
    }

    private fun setListeners() {
        btnSkip!!.setOnClickListener {
//            launchHomeScreen()
            showAlertDialog(ALERT_DIALOG_OPENED)
        }
        btnNext!!.setOnClickListener {
            val current = getItem(+1)
            if (current < layouts!!.size) {
                viewPager!!.currentItem = current
            } else {
//                launchHomeScreen()
                showAlertDialog(ALERT_DIALOG_OPENED)
            }
        }
    }


    @SuppressLint("ResourceType")
    private fun showAlertDialog(alertDialogOpened: Int) {
        val isDialogOpen = alertDialogOpened == ALERT_DIALOG_OPENED

        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            val inflater = getLayoutInflater()
            val dialogView = inflater.inflate(R.layout.form_data, null)
            alertDialogBuilder.setView(dialogView)
            alertDialogBuilder.setCancelable(true)

            txtName = dialogView.findViewById<View>(R.id.txt_nama) as AppCompatEditText
            btnYes = dialogView.findViewById<View>(R.id.btnYes) as Button
            btnYes.setOnClickListener {
                valueNama = txtName.text.toString().trim()

                val sp: SharedPreferences = getSharedPreferences("MyKey", MODE_PRIVATE)
                val preferencesEditor = sp.edit()
                preferencesEditor.putString("tag", valueNama)
                preferencesEditor.apply()

                if (valueNama != ""){
                    launchHomeScreen()
                }else{
                     Toast.makeText(this@WelcomeActivity,"isi identitas user!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun addBottomDots(currentPage: Int) {
        dots = arrayOfNulls(layouts!!.size)
        val colorsActive = resources.getIntArray(R.array.array_dot_active)
        val colorsInactive = resources.getIntArray(R.array.array_dot_inactive)
        dotsLayout!!.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]!!.text = Html.fromHtml("&#8226;")
            dots[i]!!.textSize = 35f
            dots[i]!!.setTextColor(colorsInactive[currentPage])
            dotsLayout!!.addView(dots[i])
        }
        if (dots.isNotEmpty())
            dots[currentPage]!!.setTextColor(colorsActive[currentPage])
    }

    private fun getItem(i: Int): Int {
        return viewPager!!.currentItem + i
    }

    private fun launchHomeScreen() {
        prefManager.setNotFirstTime(true)
        openMainScreen()
    }

    private fun openMainScreen() {
        val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
        val name = valueNama
        intent.putExtra(MainActivity.EXTRA_NAME, name)
        startActivity(intent)
        finish()
    }



    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    inner class MyViewPagerAdapter : PagerAdapter() {
        private var layoutInflater: LayoutInflater? = null

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater!!.inflate(layouts!![position], container, false)
            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return layouts!!.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }
}
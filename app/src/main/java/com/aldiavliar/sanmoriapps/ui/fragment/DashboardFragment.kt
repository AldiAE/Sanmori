package com.aldiavliar.sanmoriapps.ui.fragment

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.ui.main.belajar.MenuBelajarActivity
import com.aldiavliar.sanmoriapps.ui.main.homeslider.SliderImageAdapter
import com.aldiavliar.sanmoriapps.ui.main.quiz.activity.MenuQuizActivity
import com.aldiavliar.sanmoriapps.ui.main.sku.MenuSkuActivity
import com.aldiavliar.sanmoriapps.ui.main.suara.MenuSuaraActivity
import com.aldiavliar.sanmoriapps.ui.main.terjemah.MenuPenerjemahActivity
import com.aldiavliar.sanmoriapps.ui.main.video.MenuVideoActivity
import com.aldiavliar.sanmoriapps.ui.main.video.VideoApi
import com.aldiavliar.sanmoriapps.ui.main.video.model.Snippet
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.slider_imageview.*
import org.json.JSONException
import org.json.JSONObject
import java.text.ParseException
import java.util.*


class DashboardFragment : Fragment() {

    companion object {
        const val ALERT_DIALOG_OPENED = 10
    }

    private lateinit var kalender: Calendar
    private lateinit var waktu: TextView
    var modelSlider: MutableList<Snippet> = ArrayList()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        return view
    }

    private fun getSlideData() {
        AndroidNetworking.get(VideoApi.ListVideo)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            val playerArray = response.getJSONArray("items")
                            for (i in 0 until playerArray.length()) {
                                val jsonObject1 = playerArray.getJSONObject(i)
                                val dataApi = Snippet()
                                val jsonObject2 = jsonObject1.getJSONObject("snippet")
                                val jsonObject4 = jsonObject2.getJSONObject("thumbnails")
                                val defaultUrl = jsonObject4.getJSONObject("high")
                                val url = defaultUrl.getString("url")
                                dataApi.url = Uri.parse("$url").toString()

                                modelSlider.add(dataApi)

                                showSlider()
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                            Toast.makeText(
                                    context,
                                    "Gagal menampilkan data!", Toast.LENGTH_SHORT
                            ).show()
                        } catch (e: ParseException) {
                            e.printStackTrace()
                            Toast.makeText(
                                    context,
                                    "Gagal menampilkan data!", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onError(anError: ANError) {
                        Toast.makeText(
                                context,
                                "Request harian limit / Tidak ada jaringan internet!", Toast.LENGTH_SHORT
                        ).show()
                    }
                })
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun showSlider() {
        val sliderImageAdapter = SliderImageAdapter(context, modelSlider)
        sliderImageAdapter.count = modelSlider.size
        imgSlider.setSliderAdapter(sliderImageAdapter)
        imgSlider.setIndicatorAnimation(IndicatorAnimations.DROP)
        imgSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        imgSlider.indicatorSelectedColor = Color.WHITE
        imgSlider.indicatorUnselectedColor = Color.TRANSPARENT
        imgSlider.startAutoCycle()
        imgSlider.setOnIndicatorClickListener { position -> imgSlider.currentPagePosition = position }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getSlideData()


        val name = activity?.intent?.getStringExtra("extra_name")
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("MyKey", MODE_PRIVATE)
        val value = sharedPreferences.getString("tag", name)
        tvWelcome.setText("Hi, " + value)

        waktu = view.findViewById(R.id.tvNameWaktu)
        kalender = Calendar.getInstance()
        var jam = kalender.get(Calendar.HOUR_OF_DAY)

        if (jam >= 0 && jam < 12){
            waktu.setText("Selamat Pagi")
        }
        else if (jam >= 12 && jam < 16){
            waktu.setText("Selamat Siang")
        }
        else if (jam >= 16 && jam < 21){
            waktu.setText("Selamat Sore")
        }
        else if (jam >= 21 && jam < 24){
            waktu.setText("Selamat Malam")
        }
        else {
            waktu.setText("Hallo!!")
        }


        val belajar = view.findViewById(R.id.belajar) as LinearLayout
        belajar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(getActivity(), MenuBelajarActivity::class.java)
                startActivity(intent)
            }
        })

        val video = view.findViewById(R.id.video) as LinearLayout
        video.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(getActivity(), MenuVideoActivity::class.java)
                startActivity(intent)
            }
        })

        val suara = view.findViewById(R.id.suara) as LinearLayout
        suara.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(getActivity(), MenuSuaraActivity::class.java)
                startActivity(intent)
            }
        })

        val penerjemah = view.findViewById(R.id.penerjemah) as LinearLayout
        penerjemah.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(getActivity(), MenuPenerjemahActivity::class.java)
                startActivity(intent)
            }
        })

        val quiz = view.findViewById(R.id.quiz) as LinearLayout
        quiz.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(getActivity(), MenuQuizActivity::class.java)
                startActivity(intent)
            }
        })

        val sku = view.findViewById(R.id.sku) as LinearLayout
        sku.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(getActivity(), MenuSkuActivity::class.java)
                startActivity(intent)
            }
        })

        manualBook.setOnClickListener {
            showAlertDialog(ALERT_DIALOG_OPENED)
        }
    }

    private fun showAlertDialog(type: Int) {
        val isDialogOpen = type == ALERT_DIALOG_OPENED
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogOpen) {
            dialogTitle = getString(R.string.tutorial)
            dialogMessage = getString(R.string.message_tutorial)
        } else {
            dialogMessage = getString(R.string.message_cancel)
            dialogTitle = getString(R.string.cancel)
        }
        val alertDialogBuilder = context?.let { AlertDialog.Builder(it) }
        with(alertDialogBuilder) {
            this?.setTitle(dialogTitle)
            this?.setMessage(dialogMessage)
            this?.setCancelable(false)
            this?.setPositiveButton(getString(R.string.yes)) { _, _ ->
                if (isDialogOpen) {
                    val uri = Uri.parse("https://www.youtube.com/playlist?list=PLdMJ7LDvnygYu4FpdZMtRjILCex-_IJsL")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }
            this?.setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder?.create()
        alertDialog?.show()
    }
}


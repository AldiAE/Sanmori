package com.aldiavliar.sanmoriapps.ui.main.video


import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.ui.main.video.model.*
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_menu_video.*
import org.json.JSONException
import org.json.JSONObject
import java.text.ParseException
import java.text.SimpleDateFormat


@Suppress("DEPRECATION")
class MenuVideoActivity : AppCompatActivity() {


    private lateinit var back : ImageButton
    var videoAdapter: MenuVideoAdapter? = null
    var mProgressBar: ProgressDialog? = null
    var snippet: MutableList<Snippet> = ArrayList()
    private var isSearch = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_video)

        back =  findViewById(R.id.onPressedBack)
        back.setOnClickListener {
            super.onBackPressed()
        }

        mProgressBar = ProgressDialog(this, R.style.AppCompatAlertDialogStyle)
        mProgressBar!!.setTitle("Mohon Tunggu")
        mProgressBar!!.setCancelable(true)
        mProgressBar!!.setMessage("Sedang menampilkan data...")

        rvListVideo.setHasFixedSize(true)
        rvListVideo.setLayoutManager(LinearLayoutManager(this))

        searchVideo.queryHint
        searchVideo.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                setSearchVideo(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText == "") {
                    listVideo
                }
                return false
            }
        })

        val searchPlateId = searchVideo.getContext().resources
                .getIdentifier("android:id/search_plate", null, null)
        val searchPlate = searchVideo.findViewById<View>(searchPlateId)
        searchPlate?.setBackgroundColor(Color.TRANSPARENT!!)

        //get data
        listVideo

    }

    private val listVideo: Unit
        private get() {
            isSearch = false
            mProgressBar!!.show()
            AndroidNetworking.get(VideoApi.ListVideo)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(object : JSONObjectRequestListener {
                        override fun onResponse(response: JSONObject) {
                            try {
                                mProgressBar!!.dismiss()
                                snippet = ArrayList()
                                val playerArray = response.getJSONArray("items")
                                for (i in 0 until playerArray.length()) {
                                    val jsonObject1 = playerArray.getJSONObject(i)
                                    val dataApi = Snippet()

                                    val jsonObject2 = jsonObject1.getJSONObject("snippet")
                                    dataApi.title = jsonObject2.getString("title")
                                    dataApi.description = jsonObject2.getString("description")


                                    val datePost = jsonObject2.getString("publishedAt")
                                    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                    val outputFormat = SimpleDateFormat("dd-MM-yyyy")
                                    val date = inputFormat.parse(datePost)
                                    val datePostConvert = outputFormat.format(date)
                                    dataApi.publishedAt = datePostConvert

                                    val jsonObject3 = jsonObject1.getJSONObject("id")
                                    val videoId = jsonObject3.getString("videoId")
                                    dataApi.videoId = videoId

                                    val jsonObject4 = jsonObject2.getJSONObject("thumbnails")
                                    val defaultUrl = jsonObject4.getJSONObject("high")
                                    val url = defaultUrl.getString("url")
                                    dataApi.url = Uri.parse("$url").toString()
                                    snippet.add(dataApi)
                                    showListVideo()

                                }
                            } catch (e: JSONException) {
                                e.printStackTrace()
//                                Toast.makeText(this@MenuVideoActivity,"Gagal menampilkan data!", Toast.LENGTH_SHORT).show()
                            } catch (e: ParseException) {
                                e.printStackTrace()
//                                Toast.makeText(this@MenuVideoActivity,"Gagal menampilkan data!", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onError(anError: ANError) {
                            mProgressBar!!.dismiss()
                            Toast.makeText(this@MenuVideoActivity,
                                    "Request harian limit / Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show()
                        }
                    })
        }

    private fun setSearchVideo(query: String) {
        isSearch = true
        mProgressBar?.show()
        AndroidNetworking.get("${VideoApi.SEARCHURl}&q=$query&${VideoApi.TYPE}")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            mProgressBar?.dismiss()
                            if (snippet.isNotEmpty()) snippet.clear()
                            val jsonArray = response.getJSONArray("items")
                            for (i in 0 until jsonArray.length()) {
                                val jsonObject1 = jsonArray.getJSONObject(i)
                                val dataApi = Snippet()

                                val jsonObject2 = jsonObject1.getJSONObject("snippet")
                                dataApi.title = jsonObject2.getString("title")
                                dataApi.description = jsonObject2.getString("description")


                                val datePost = jsonObject2.getString("publishedAt")
                                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                val outputFormat = SimpleDateFormat("dd-MM-yyyy")
                                val date = inputFormat.parse(datePost)
                                val datePostConvert = outputFormat.format(date)
                                dataApi.publishedAt = datePostConvert

                                val jsonObject3 = jsonObject1.getJSONObject("id")
                                val videoId = jsonObject3.getString("videoId")
                                dataApi.videoId = videoId

                                val jsonObject4 = jsonObject2.getJSONObject("thumbnails")
                                val defaultUrl = jsonObject4.getJSONObject("default")
                                val url = defaultUrl.getString("url")
                                dataApi.url = Uri.parse("$url").toString()
                                snippet.add(dataApi)

                            }
                            showListVideo()
                        } catch (e: JSONException) {
                            e.printStackTrace()
                            Toast.makeText(this@MenuVideoActivity, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(anError: ANError) {
                        mProgressBar?.dismiss()
                        Toast.makeText(this@MenuVideoActivity, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show()
                    }
                })
    }

    private fun showListVideo() {
        videoAdapter = MenuVideoAdapter(this@MenuVideoActivity, snippet)
        rvListVideo!!.adapter = videoAdapter
        videoAdapter?.setOnItemClickCallback(object : OnItemClickCallback {

            override fun onItemClicked(snippet: Snippet) {
                val intent = Intent(this@MenuVideoActivity, DetailVideoActivity::class.java)
                val videoId = snippet.videoId
                intent.putExtra(DetailVideoActivity.EXTRA_ID, videoId)
                intent.putExtra("detailVideo", snippet)
                startActivity(intent)
            }
        })
    }
}



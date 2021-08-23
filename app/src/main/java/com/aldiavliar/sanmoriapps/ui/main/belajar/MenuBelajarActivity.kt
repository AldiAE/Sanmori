package com.aldiavliar.sanmoriapps.ui.main.belajar

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
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_menu_belajar.*
import org.json.JSONException
import org.json.JSONObject
import java.text.ParseException
import java.text.SimpleDateFormat

@Suppress("DEPRECATION")
class MenuBelajarActivity : AppCompatActivity(){

    private lateinit var back : ImageButton
    var belajarAdapter: MenuBelajarAdapter? = null
    var mProgressBar: ProgressDialog? = null
    var modelBelajar: MutableList<ModelBelajar> = ArrayList()
    private var isSearch = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_belajar)

        back =  findViewById(R.id.onPressedBack)
        back.setOnClickListener {
            super.onBackPressed()
        }

        mProgressBar = ProgressDialog(this, R.style.AppCompatAlertDialogStyle)
        mProgressBar!!.setTitle("Mohon Tunggu")
        mProgressBar!!.setCancelable(true)
        mProgressBar!!.setMessage("Sedang menampilkan data...")

        rvListArticles.setHasFixedSize(true)
        rvListArticles.setLayoutManager(LinearLayoutManager(this))

        searchBlog.queryHint
        searchBlog.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                setSearchBlog(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText == "") {
                    listBlog
                }
                return false
            }
        })

        val searchPlateId = searchBlog.getContext().resources
                .getIdentifier("android:id/search_plate", null, null)
        val searchPlate = searchBlog.findViewById<View>(searchPlateId)
        searchPlate?.setBackgroundColor(Color.TRANSPARENT!!)

        //get data
        listBlog
    }

    private val listBlog: Unit
    private get() {
        isSearch = false
        mProgressBar!!.show()
        AndroidNetworking.get(BloggerApi.ListPost)
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        mProgressBar!!.dismiss()
                        modelBelajar = ArrayList()
                        val playerArray = response.getJSONArray("items")
                        for (i in 0 until playerArray.length()) {
                            val jsonObject1 = playerArray.getJSONObject(i)
                            val dataApi = ModelBelajar()

                            dataApi.title = jsonObject1.getString("title")
                            dataApi.content = jsonObject1.getString("content")
                            dataApi.url = jsonObject1.getString("url")

                            val datePost = jsonObject1.getString("published")
                            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                            val outputFormat = SimpleDateFormat("dd-MM-yyyy")
                            val date = inputFormat.parse(datePost)
                            val datePostConvert = outputFormat.format(date)
                            dataApi.published = datePostConvert

                            val jsonObject2 = jsonObject1.getJSONObject("author")
                            val authorPost = jsonObject2.getString("displayName")
                            dataApi.author = authorPost

                            val jsonObject3 = jsonObject2.getJSONObject("image")
                            val authorImage = jsonObject3.getString("url")
                            dataApi.authorImage = Uri.parse("http:$authorImage").toString()
                            modelBelajar.add(dataApi)

                            showListBlog()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Toast.makeText(this@MenuBelajarActivity,
                            "Gagal menampilkan data!", Toast.LENGTH_SHORT).show()
                    } catch (e: ParseException) {
                        e.printStackTrace()
                        Toast.makeText(this@MenuBelajarActivity,
                            "Gagal menampilkan data!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError) {
                    mProgressBar!!.dismiss()
                    Toast.makeText(this@MenuBelajarActivity,
                        "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    private fun setSearchBlog(query: String) {
        isSearch = true
        mProgressBar?.show()
        AndroidNetworking.get("${BloggerApi.SearchPost}q=$query&key=${BloggerApi.API_KEY}")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            mProgressBar?.dismiss()
                            if (modelBelajar.isNotEmpty()) modelBelajar.clear()
                            val jsonArray = response.getJSONArray("items")
                            for (i in 0 until jsonArray.length()) {
                                val jsonObject = jsonArray.getJSONObject(i)
                                val dataApi = ModelBelajar()
                                dataApi.title = jsonObject.getString("title")
                                dataApi.content = jsonObject.getString("content").replace("· ", "")
                                dataApi.url = jsonObject.getString("url")

                                val datePost = jsonObject.getString("published")
                                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                val outputFormat = SimpleDateFormat("dd-MM-yyyy")
                                val date = inputFormat.parse(datePost)
                                val datePostConvert = outputFormat.format(date)
                                dataApi.published = datePostConvert

                                val jsonObject2 = jsonObject.getJSONObject("author")
                                val authorPost = jsonObject2.getString("displayName")
                                dataApi.author = authorPost

                                val jsonObject3 = jsonObject2.getJSONObject("image")
                                val authorImage = jsonObject3.getString("url")
                                dataApi.authorImage = Uri.parse("http:$authorImage").toString()
                                modelBelajar.add(dataApi)
                            }
                            showListBlog()

                        } catch (e: JSONException) {
                            e.printStackTrace()
                            Toast.makeText(this@MenuBelajarActivity, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(anError: ANError) {
                        mProgressBar?.dismiss()
                        Toast.makeText(this@MenuBelajarActivity, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show()
                    }
                }
        )
    }

    private fun showListBlog() {
        belajarAdapter = MenuBelajarAdapter(this@MenuBelajarActivity, modelBelajar)
        rvListArticles!!.adapter = belajarAdapter
        belajarAdapter?.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClicked(modelBelajar: ModelBelajar) {
                val intent = Intent(this@MenuBelajarActivity, DetailArtikelActivity::class.java)
                intent.putExtra("detailArtikel", modelBelajar)
                startActivity(intent)
            }
        })
    }
}
package com.aldiavliar.sanmoriapps.ui.main.belajar

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.aldiavliar.sanmoriapps.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_detail_artikel.*
import org.jsoup.Jsoup

@Suppress("DEPRECATION")
class DetailArtikelActivity : AppCompatActivity() {
    var modelBelajar: ModelBelajar? = null
    var strTitle: String? = null
    var strDate: String? = null
    var strKetDetail: String? = null
    var strImgDetail: String? = null
    var strUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_artikel)

        setSupportActionBar(toolbar_detail)
        assert(supportActionBar != null)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = null

        progressBar.max = 100
        modelBelajar= intent.getSerializableExtra("detailArtikel") as ModelBelajar
        progressBar.progress = 0

        if (modelBelajar != null) {
            strTitle = modelBelajar!!.title
            strDate = modelBelajar!!.published
            strKetDetail = modelBelajar!!.content
            strUrl = modelBelajar!!.url

            val document = Jsoup.parse(modelBelajar!!.content.also { strImgDetail = it })
            val element = document.select("img")
            Glide.with(this)
                .load(element[0].attr("src"))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgDetail)

            progressBar.visibility = View.GONE

            tvTitle.text = strTitle
            tvTitle.isSelected = true
            tvDate.text = strDate


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                tvKetDetail.text = Html.fromHtml(strKetDetail,
                    Html.FROM_HTML_MODE_LEGACY) else {
                tvKetDetail.text = Html.fromHtml(strKetDetail)
            }
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
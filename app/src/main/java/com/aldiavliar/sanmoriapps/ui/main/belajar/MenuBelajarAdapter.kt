package com.aldiavliar.sanmoriapps.ui.main.belajar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aldiavliar.sanmoriapps.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.list_item_artikel.view.*
import org.jsoup.Jsoup

class MenuBelajarAdapter(private val context: Context, private val items: List<ModelBelajar>) :
        RecyclerView.Adapter<MenuBelajarAdapter.ViewHolder>() {


    private var onItemClickCallback: OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback?) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_artikel, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelBelajar = items[position]
        //Get Image

        //Get Image
        val document = Jsoup.parse(modelBelajar.content)
        val element = document.select("img")
        if (context != null) {
            Glide.with(context)
                    .load(element[0].attr("src"))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(holder.imgThumb)
        }
        if (context != null) {
            Glide.with(context)
                    .load(modelBelajar.authorImage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(holder.imgAuthor)
            holder.tvTitle.setText(modelBelajar.title)
            holder.tvAuthor.setText(modelBelajar.author)
            holder.tvDatePost.setText(modelBelajar.published)
            holder.rlArtikel.setOnClickListener {
                onItemClickCallback?.onItemClicked(modelBelajar)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvAuthor: TextView
        var tvDatePost: TextView
        var rlArtikel: RelativeLayout
        var imgThumb: ImageView
        var imgAuthor: ImageView

        init {
            tvTitle = itemView.tvTitle
            tvAuthor = itemView.tvAuthor
            tvDatePost = itemView.tvDatePost
            rlArtikel = itemView.rlArtikel
            imgThumb = itemView.imgThumb
            imgAuthor = itemView.imgAuthor
        }
    }

}
package com.aldiavliar.sanmoriapps.ui.main.video

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.ui.main.video.model.Snippet
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.list_item_video.view.*

class MenuVideoAdapter (private val context: Context, private val items: List<Snippet>) :
        RecyclerView.Adapter<MenuVideoAdapter.ViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback?) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_video, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val snippet = items[position]
        //Get Image

        //Get Image
        if (context != null) {
            Glide.with(context)
                    .load(snippet.url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(holder.imgThumb)
            holder.tvTitle.setText(snippet.title)
            holder.tvPublished.setText(snippet.publishedAt)
            holder.tvDesc.setText(snippet.description)
            holder.btnWatch.setOnClickListener {
                onItemClickCallback?.onItemClicked(snippet)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvPublished: TextView
        var tvDesc: TextView
        var rlYoutube: RelativeLayout
        var imgThumb: ImageView
        var btnWatch: AppCompatButton

        init {
            tvTitle = itemView.tvTitle
            tvPublished = itemView.tvPublished
            tvDesc = itemView.tvDesc
            rlYoutube = itemView.rlYoutube
            imgThumb = itemView.imgThumb
            btnWatch = itemView.btnWatch
        }
    }
}
package com.aldiavliar.sanmoriapps.ui.main.homeslider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.ui.main.video.model.Snippet
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderImageAdapter(mContext: Context?, mSliderItems: MutableList<Snippet>) : SliderViewAdapter<SliderImageAdapter.SliderAdapterVH?>() {
    private var mContext: Context? = null
    private val mSliderItems: MutableList<Snippet>
    private var mCount = 0
    fun setCount(count: Int) {
        mCount = count
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_slider, null)
        return SliderAdapterVH(view)
    }

    override fun getCount(): Int {
        return mCount
    }

    inner class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {

        var imgAutoSlider: ImageView

        init {
            imgAutoSlider = itemView.findViewById(R.id.imgAutoSlider)

        }
    }

    init {
        if (mContext != null) {
            this.mContext = mContext
        }
        this.mSliderItems = mSliderItems
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH?, position: Int) {
        val sliderItem = mSliderItems[position]
        viewHolder?.let {
            Glide.with(it.itemView)
                .load(sliderItem.url)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imgAutoSlider)
        }
    }
}
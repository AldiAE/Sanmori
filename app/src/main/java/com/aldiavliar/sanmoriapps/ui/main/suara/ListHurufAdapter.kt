package com.aldiavliar.sanmoriapps.ui.main.suara

import android.media.AudioManager
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aldiavliar.sanmoriapps.data.Entity.HurufEntity
import com.aldiavliar.sanmoriapps.data.helper.HurufDiffCallback
import com.aldiavliar.sanmoriapps.databinding.ItemListHurufBinding
import java.io.IOException

class ListHurufAdapter : RecyclerView.Adapter<ListHurufAdapter.ListViewHolder>() {

    private var listHuruf = ArrayList<HurufEntity>()

    fun setSoundHuruf(soundhuruf: List<HurufEntity>) {
        val diffCallback = HurufDiffCallback(this.listHuruf, soundhuruf)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        if (soundhuruf == null) return
        this.listHuruf.clear()
        this.listHuruf.addAll(soundhuruf)
        diffResult.dispatchUpdatesTo(this)
    }

    private var mMediaPlayer = MediaPlayer()

    inner class ListViewHolder(private val binding: ItemListHurufBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hurufEntity: HurufEntity) {
            with(binding){
                hurufId.text = hurufEntity.huruf
                sandiId.text = hurufEntity.sandi
                btnPlay.setOnClickListener(View.OnClickListener {
                    playAudio(hurufEntity)
                })
                btnStop.setOnClickListener(View.OnClickListener {
                    if (mMediaPlayer.isPlaying()) {

                        mMediaPlayer.stop()
                        mMediaPlayer.reset()
                        mMediaPlayer.release()

                    } else {
                        mMediaPlayer.stop()
                    }

                })
            }
        }

        private fun playAudio(hurufEntity: HurufEntity) {
            val audioUrl = hurufEntity.sound
            mMediaPlayer = MediaPlayer()
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

            try {
                mMediaPlayer.run {
                    setDataSource(audioUrl)

                    prepare()
                    start()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListHurufBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHuruf[position])
    }

    override fun getItemCount(): Int = listHuruf.size
    fun setFilter(dataFilter: java.util.ArrayList<HurufEntity>) {
        listHuruf = ArrayList()
        listHuruf.addAll(dataFilter)
        notifyDataSetChanged()
    }

}
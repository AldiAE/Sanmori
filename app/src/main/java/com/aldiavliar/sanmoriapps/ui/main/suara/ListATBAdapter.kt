package com.aldiavliar.sanmoriapps.ui.main.suara

import android.media.AudioManager
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aldiavliar.sanmoriapps.data.Entity.ATBEntity
import com.aldiavliar.sanmoriapps.data.helper.ATBDiffCallback
import com.aldiavliar.sanmoriapps.databinding.ItemListAtbBinding
import java.io.IOException

class ListATBAdapter : RecyclerView.Adapter<ListATBAdapter.ListViewHolder>() {

    private var listATB = ArrayList<ATBEntity>()

    fun setSoundATB(soundatb: List<ATBEntity>) {
        val diffCallback = ATBDiffCallback(this.listATB, soundatb)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        if (soundatb == null) return
        this.listATB.clear()
        this.listATB.addAll(soundatb)
        diffResult.dispatchUpdatesTo(this)
    }

    private var mMediaPlayer = MediaPlayer()

    inner class ListViewHolder(private val binding: ItemListAtbBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(atbEntity: ATBEntity) {
            with(binding){
                atbId.text = atbEntity.atb
                sandiAtbId.text = atbEntity.sandi
                btnPlay.setOnClickListener(View.OnClickListener {
                    playAudio(atbEntity)
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
    }

    private fun playAudio(atbEntity: ATBEntity) {
        val audioUrl = atbEntity.sound
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListAtbBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listATB[position])
    }

    override fun getItemCount(): Int = listATB.size

}
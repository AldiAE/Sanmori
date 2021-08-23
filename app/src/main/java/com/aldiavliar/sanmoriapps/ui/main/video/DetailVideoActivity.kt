package com.aldiavliar.sanmoriapps.ui.main.video

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.ui.main.video.model.Snippet
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_detail_video.*


class DetailVideoActivity : AppCompatActivity() {

    private lateinit var youtubePlayerView: YouTubePlayerView
    var snippet: Snippet? = null
    var strVideoId: String? = null
    var strDateVideo: String? = null
    var strDescVideo: String? = null

    companion object {
        const val EXTRA_ID= "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_video)

        snippet= intent.getSerializableExtra("detailVideo") as Snippet
        youtubePlayerView = findViewById(R.id.youtube_player_view)

        if (snippet != null) {
            strVideoId = snippet!!.videoId
            strDescVideo = snippet!!.description
            strDateVideo = snippet!!.publishedAt

            tvDate.text = strDateVideo
            tvDescVideo.text = strDescVideo

            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                    val videoId = intent.getStringExtra(EXTRA_ID).toString()
                    videoId.let {
                        youTubePlayer.loadVideo(it, 0F)
                    }

                }
            })


        }
    }

}
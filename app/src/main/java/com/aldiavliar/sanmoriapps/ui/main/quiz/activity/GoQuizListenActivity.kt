package com.aldiavliar.sanmoriapps.ui.main.quiz.activity

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.ui.main.quiz.adapter.QuestionListenAdapter
import com.aldiavliar.sanmoriapps.ui.main.quiz.model.QuestionListenModel
import com.aldiavliar.sanmoriapps.ui.main.quiz.model.QuizModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_go_quiz_listen.*
import kotlinx.android.synthetic.main.activity_go_quiz_listen.btnNext
import kotlinx.android.synthetic.main.activity_go_quiz_listen.btnPrevious
import kotlinx.android.synthetic.main.activity_go_quiz_listen.btnSubmit
import kotlinx.android.synthetic.main.activity_go_quiz_listen.optionList
import kotlinx.android.synthetic.main.activity_go_quiz_listen.toolbar_title_quiz
import java.io.IOException

class GoQuizListenActivity : AppCompatActivity() {

    private var mMediaPlayer = MediaPlayer()
    var quiz : MutableList<QuizModel>? = null
    var questionsListen: MutableMap<String, QuestionListenModel>? = null
    var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_quiz_listen)


        setUpView()
    }

    private fun setUpView() {
        setUpFirestore()
        setUpEventListener()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
//        finish()
    }

    private fun setUpEventListener() {
        btnPrevious.setOnClickListener {
            index--
            bindViews()
        }

        btnNext.setOnClickListener {
            index++
            bindViews()
        }

        btnSubmit.setOnClickListener {
            Log.d("FINALQUIZ", questionsListen.toString())

            val intent = Intent(this, ResultActivity::class.java)
            val json  = Gson().toJson(quiz!![0])
            intent.putExtra("QUIZ", json)
            startActivity(intent)
            finish()
        }
    }

    private fun bindViews() {
        btnPrevious.visibility = View.GONE
        btnSubmit.visibility = View.GONE
        btnNext.visibility = View.GONE

        if(index == 1){ //first question
            btnNext.visibility = View.VISIBLE
        }
        else if(index == questionsListen!!.size) { // last question
            btnSubmit.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        }
        else{ // Middle
            btnPrevious.visibility = View.VISIBLE
            btnNext.visibility = View.VISIBLE
        }

        val question = questionsListen!!["question$index"]
        question?.let {
            btn_play_quiz.setOnClickListener {
                playAudio(question)
            }
            btn_stop_quiz.setOnClickListener {
                if (mMediaPlayer.isPlaying()) {

                    mMediaPlayer.stop()
                    mMediaPlayer.reset()
                    mMediaPlayer.release()

                } else {
                    mMediaPlayer.stop()
                }
            }
            val questionAdapter = QuestionListenAdapter(this, it)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = questionAdapter
            optionList.setHasFixedSize(true)
        }
    }

    private fun playAudio(questionListenModel: QuestionListenModel) {
        val audioUrl = questionListenModel.description
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

    private fun setUpFirestore() {
        val firestore = FirebaseFirestore.getInstance()
        var title = intent.getStringExtra("TITLE")
        toolbar_title_quiz.text = title
        if (title != null) {
            firestore.collection("quiz").whereEqualTo("title", title)
                .get()
                .addOnSuccessListener {
                    if(it != null && !it.isEmpty){
                        quiz = it.toObjects(QuizModel::class.java)
                        questionsListen = quiz!![0].questionsListen
                        bindViews()
                    }
                }
        }
    }
}
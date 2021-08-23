package com.aldiavliar.sanmoriapps.ui.main.quiz.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.ui.main.quiz.model.QuizModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    lateinit var quiz: QuizModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setUpViews()

        btnBack.setOnClickListener {
            startActivity(Intent(this, MenuQuizActivity::class.java))
            finish()
        }


    }

    override fun onBackPressed() {

    }

    private fun setUpViews() {
        val quizData = intent.getStringExtra("QUIZ")
        quiz = Gson().fromJson<QuizModel>(quizData, QuizModel::class.java)
        calculateScore()
        setAnswerView()
    }

    private fun setAnswerView() {
        val builder = StringBuilder("")
        for (entry in quiz.questions.entries) {
            val question = entry.value
            builder.append("<font color'#964B00'><b>Kategori: TIPE READING</b></font><br/>")
            builder.append("<font color'#18206F'><b>Soal: ${question.description}</b></font><br/>")
            builder.append("<font color='#009688'>Jawaban: ${question.answer}</font><br/><br/>")
        }
        for (entry in quiz.questionsListen.entries) {
            val question = entry.value
            builder.append("<font color'#964B00'><b>Kategori: TIPE LISTENING</b></font><br/>")
            builder.append("<font color'#18206F'><b>Soal: ${question.description}</b></font><br/>")
            builder.append("<font color='#009688'>Jawaban: ${question.answer}</font><br/><br/>")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            txtAnswer.text = Html.fromHtml(builder.toString());
        }
    }

    private fun calculateScore() {
        var score = 0
        for (entry in quiz.questions.entries) {
            val question = entry.value
            if (question.answer == question.userAnswer) {
                score += 10
            }
        }
        for (entry in quiz.questionsListen.entries) {
            val question = entry.value
            if (question.answer == question.userAnswer) {
                score += 10
            }
        }
        txtScore.text = "Nilai Quiz : $score"
    }
}
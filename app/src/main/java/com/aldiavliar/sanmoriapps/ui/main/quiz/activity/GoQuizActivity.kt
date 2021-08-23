package com.aldiavliar.sanmoriapps.ui.main.quiz.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.ui.main.quiz.adapter.QuestionAdapter
import com.aldiavliar.sanmoriapps.ui.main.quiz.model.QuestionModel
import com.aldiavliar.sanmoriapps.ui.main.quiz.model.QuizModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_go_quiz.*

class GoQuizActivity : AppCompatActivity() {

    var quiz : MutableList<QuizModel>? = null
    var questions: MutableMap<String, QuestionModel>? = null
    var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_quiz)



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
            Log.d("FINALQUIZ", questions.toString())

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
        else if(index == questions!!.size) { // last question
            btnSubmit.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        }
        else{ // Middle
            btnPrevious.visibility = View.VISIBLE
            btnNext.visibility = View.VISIBLE
        }

        val question = questions!!["question$index"]
        question?.let {
            description.text = it.description
            val questionAdapter = QuestionAdapter(this, it)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = questionAdapter
            optionList.setHasFixedSize(true)
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
                        questions = quiz!![0].questions
                        bindViews()
                    }
                }
        }
    }


}
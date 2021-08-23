package com.aldiavliar.sanmoriapps.ui.main.quiz.activity

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.ui.main.quiz.adapter.QuizAdapter
import com.aldiavliar.sanmoriapps.ui.main.quiz.model.QuizModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_menu_quiz.*

class MenuQuizActivity : AppCompatActivity() {

    private lateinit var back : ImageButton
    lateinit var adapter: QuizAdapter
    private var quizList = mutableListOf<QuizModel>()
    lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_quiz)

        back =  findViewById(R.id.onPressedBack)
        back.setOnClickListener {
            super.onBackPressed()
            finish()
        }

        swipeRefresh.setOnRefreshListener {
            setUpViews()
        }

        setUpViews()
    }

    override fun onBackPressed() {
        finish()
    }

    private fun setUpViews() {
        swipeRefresh.isRefreshing = true
        setUpFirestore()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        swipeRefresh.isRefreshing = false
        adapter = QuizAdapter(this, quizList)
        quizRecyclerView.layoutManager = GridLayoutManager(this, 2)
        quizRecyclerView.adapter = adapter
    }

    private fun setUpFirestore() {
        swipeRefresh.isRefreshing = false
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quiz")
        collectionReference.addSnapshotListener { value, error ->
            if(value == null || error != null){
                Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA", value.toObjects(QuizModel::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(QuizModel::class.java))
            adapter.notifyDataSetChanged()
        }
    }

}
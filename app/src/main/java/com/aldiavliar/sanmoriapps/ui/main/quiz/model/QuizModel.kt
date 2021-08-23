package com.aldiavliar.sanmoriapps.ui.main.quiz.model

data class QuizModel (
    var id : String = "",
    var title: String = "",
    var questions: MutableMap<String, QuestionModel> = mutableMapOf(),
    var questionsListen: MutableMap<String, QuestionListenModel> = mutableMapOf()
)
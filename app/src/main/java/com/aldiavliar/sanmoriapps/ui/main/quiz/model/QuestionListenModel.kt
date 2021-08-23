package com.aldiavliar.sanmoriapps.ui.main.quiz.model

data class QuestionListenModel (
    var description: String = "",
    var optA: String = "",
    var optB: String = "",
    var optC: String = "",
    var optD: String = "",
    var answer: String = "",
    var userAnswer: String = ""
)
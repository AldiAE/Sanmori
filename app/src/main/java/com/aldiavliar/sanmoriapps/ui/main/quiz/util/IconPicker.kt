package com.aldiavliar.sanmoriapps.ui.main.quiz.util

import com.aldiavliar.sanmoriapps.R

object IconPicker {
    val icons = arrayOf(
        R.drawable.ic__menu_belajar,
        R.drawable.ic_menu_video,
        R.drawable.ic_menu_quiz,
        R.drawable.ic_suaramorse,
        R.drawable.ic_terjemahkan
    )
    var currentIcon = 0

    fun getIcon(): Int {
        currentIcon = (currentIcon + 1) % icons.size
        return icons[currentIcon]
    }
}
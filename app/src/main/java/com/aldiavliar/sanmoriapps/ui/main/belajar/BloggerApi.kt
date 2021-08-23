package com.aldiavliar.sanmoriapps.ui.main.belajar

import android.util.Log
import android.widget.Toast
import com.aldiavliar.sanmoriapps.ui.main.quiz.model.QuizModel
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_menu_quiz.*
import java.util.*

object BloggerApi {

    @JvmField
    var BASE_URL = "https://www.googleapis.com/blogger/v3/blogs/"
    @JvmField
    var API_KEY = "AIzaSyA6h_nv_LO_Dma3fA1huQgJeWZ1F3MymLo"
    @JvmField
    var BLOGGER_ID = "368430654033314372"
//    368430654033314372 blog pribadi
//    880202933723933570 Materi Pramuka Blog
//    6933766314278387928 KakFasta.com
//    892387542060776006 pramuka ngeblog
//    8920496898714702653 pramuka indo
//    3691783956159499855 idn scout
    @JvmField
    var MAX = "maxResults=50"
    @JvmField
    var ListPost = "$BASE_URL$BLOGGER_ID/posts?$MAX&key=$API_KEY"
    var SearchPost = "$BASE_URL$BLOGGER_ID/posts/search?"

   // https://www.googleapis.com/blogger/v3/blogs/${BloggerApi.BLOGGER_ID}/posts/search?q=$query&key=${BloggerApi.API_KEY}

}
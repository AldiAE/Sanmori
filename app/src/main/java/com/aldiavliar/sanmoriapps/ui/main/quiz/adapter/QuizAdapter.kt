package com.aldiavliar.sanmoriapps.ui.main.quiz.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.ui.main.quiz.activity.GoQuizActivity
import com.aldiavliar.sanmoriapps.ui.main.quiz.activity.GoQuizListenActivity
import com.aldiavliar.sanmoriapps.ui.main.quiz.model.QuizModel
import com.aldiavliar.sanmoriapps.ui.main.quiz.util.ColorPicker
import com.aldiavliar.sanmoriapps.ui.main.quiz.util.IconPicker
import org.jetbrains.anko.layoutInflater

class QuizAdapter(val context: Context, val quiz: List<QuizModel>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {
    companion object {
        const val ALERT_DIALOG_OPENED = 10
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizAdapter.QuizViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_quiz, parent, false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizAdapter.QuizViewHolder, position: Int) {
        holder.textViewTitle.text = quiz[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.iconView.setImageResource(IconPicker.getIcon())
        holder.cardContainer.setOnClickListener {
            showAlertDialog(ALERT_DIALOG_OPENED, position)
        }
    }

    private fun showAlertDialog(alertDialogOpened: Int, position: Int) {
        val isDialogOpen = alertDialogOpened == ALERT_DIALOG_OPENED

        val alertDialogBuilder = AlertDialog.Builder(this.context)
        with(alertDialogBuilder) {
            val inflater = context.layoutInflater
            val dialogView = inflater.inflate(R.layout.form_mode_quiz, null)
            alertDialogBuilder.setView(dialogView)
            alertDialogBuilder.setCancelable(true)
            val btnListen = dialogView.findViewById<View>(R.id.btnListen) as Button
            val btnReading = dialogView.findViewById<View>(R.id.btnReading) as Button
            btnListen.setOnClickListener {
                requestListening(position)
            }
            btnReading.setOnClickListener {
                requestReading(position)
            }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun requestReading(position: Int) {
        Toast.makeText(context, quiz[position].title, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, GoQuizActivity::class.java)
            intent.putExtra("TITLE", quiz[position].title)
            context.startActivity(intent)
    }

    private fun requestListening(position: Int) {
        Toast.makeText(context, quiz[position].title, Toast.LENGTH_SHORT).show()
        val intent = Intent(context, GoQuizListenActivity::class.java)
        intent.putExtra("TITLE", quiz[position].title)
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return quiz.size
    }
    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.quizTitle)
        var iconView: ImageView = itemView.findViewById(R.id.quizIcon)
        var cardContainer: CardView = itemView.findViewById(R.id.cardContainer)
    }
}
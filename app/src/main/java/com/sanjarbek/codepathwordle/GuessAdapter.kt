package com.sanjarbek.codepathwordle

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GuessAdapter(val context: Context, val guessModelArrayList: ArrayList<GuessModel>): RecyclerView.Adapter<GuessAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.word_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = guessModelArrayList.get(position)

        holder.guessed_word.text = model.get_guess_word()
        holder.checked_word.text = model.get_checked_word()

    }

    override fun getItemCount(): Int {
        return guessModelArrayList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val guessed_word = itemView.findViewById<TextView>(R.id.txt_guessed_word)
        val checked_word = itemView.findViewById<TextView>(R.id.txt_checked_word)

    }

}
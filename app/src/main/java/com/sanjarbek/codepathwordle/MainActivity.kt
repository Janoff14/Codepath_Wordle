package com.sanjarbek.codepathwordle

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.sanjarbek.codepathwordle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var random_word: String


    private lateinit var viewbinding: ActivityMainBinding
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)
        random_word = FourLetterWordList.getRandomFourLetterWord()


        viewbinding.txtDisplayWord.text = random_word

        val check_btn = viewbinding.btnCheck

        val guessModelArrayList = ArrayList<GuessModel>()
        val adapter = GuessAdapter(this, guessModelArrayList)

        val recyclerView = viewbinding.rcvGuesses
        recyclerView.adapter = adapter

        var guess_num = 0
        check_btn.setOnClickListener {
            guess_num += 1

            if (guess_num > 3){
                viewbinding.txtDisplayWord.visibility = View.GONE


                adapter.guessModelArrayList.clear()

                runOnUiThread {
                    adapter.notifyDataSetChanged()
                }
                viewbinding.btnCheck.text = "Check"


                random_word = FourLetterWordList.getRandomFourLetterWord()
                viewbinding.txtDisplayWord.text = random_word
                guess_num = 0
            } else{

                val guess = viewbinding.edtGuess.text.toString().trim()
                if (guess.length != 4){
                    Toast.makeText(this, "Your guess has to be a 4-letter word!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val guessModel = GuessModel()

                    guessModel.set_guess_word(guess)
                    guessModel.set_target_word(random_word)

                    val check = checkGuess(guess)

                    guessModel.set_checked_word(check)

                    adapter.guessModelArrayList.add(guessModel)


                    runOnUiThread {
                        adapter.notifyItemInserted(adapter.guessModelArrayList.size - 1)
                    }

                    if (guess_num == 3) {
                        Toast.makeText(this, "Exceeded the number of guesses!", Toast.LENGTH_SHORT)
                            .show()
                        viewbinding.btnCheck.text = "Reset"
                        viewbinding.txtDisplayWord.visibility = View.VISIBLE
                    }
                }
            }
        }



    }
    private fun checkGuess(guess: String) : String {
        Log.d("TAG", "checkGuess: $guess $random_word")
        var result = ""
        for (i in 0..3) {
            if (guess[i].lowercase() == random_word[i].lowercase()) {
                result += "O"
            }
            else if (guess[i].lowercase() in random_word.lowercase()) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }



}

package com.sanjarbek.codepathwordle

class GuessModel {
    private lateinit var guess_word: String
    private var checked_word: String = ""
    private lateinit var target_word: String

    fun get_guess_word(): String{
        return guess_word
    }

    fun get_checked_word(): String{
        return checked_word
    }

    fun get_target_word(): String{
        return target_word
    }

    fun set_guess_word(word: String){
        guess_word = word
    }

    fun set_checked_word(word: String){
        checked_word = word
    }

    fun set_target_word(word: String){
        target_word = word
    }
}
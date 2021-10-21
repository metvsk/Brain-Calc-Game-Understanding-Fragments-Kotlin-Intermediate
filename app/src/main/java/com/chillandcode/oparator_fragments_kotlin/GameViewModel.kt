package com.chillandcode.oparator_fragments_kotlin

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import java.math.RoundingMode
import java.text.DecimalFormat

class GameViewModel : ViewModel() {
    private var counter:Int=10
    private var score:Int=0
    private var result: Float = 0.0F
    private var question: String = ""
    init {
        generateQuestion()
    }


    fun getResult(): Float {
        Log.i(TAG, "getResult: $result")
        return result
    }

    fun generateQuestion(): String {
        val x = IntRange(1, 10).random()
        val y = IntRange(1, 10).random()
        val operator = getRandomOperator()
        question = "Solve $x $operator $y "
        result=
        roundOffDecimal(getResult(operator, x.toFloat(), y.toFloat()))

        Log.i(TAG, "generateQuestion: $question result: $result  before round ${getResult(operator, x.toFloat(), y.toFloat())}")
        return question
    }

    private fun getResult(charOperator: Char, x: Float, y: Float): Float {
        return when (charOperator) {
            '*' -> (x * y)
            '/' -> (x / y)
            '+' -> (x + y)
            '-' -> (x - y)
            '%' -> (x % y)
            else -> throw Exception("That's not a supported operator")
        }

    }

    enum class OPERATOR(val symbol: Char) {
        MULTIPLY('*'), DIVIDE('/'), ADD('+'), SUBTRACT('-'), REMINDER(
            '-'
        )
    }

    private fun getRandomOperator(): Char {
//        val limit = OPERATOR.values().size - 1
//        return OPERATOR.values()[IntRange(0, limit).random()].symbol

        return '+'
    }

    fun getQuestion(): String {
        return question
    }
    fun getCounter():Int{
        return counter
    }

    fun getScore(): Int {
        return   score;
    }

    fun addPoint() {
        score++
    }

    fun reduceCounter() {
        counter--
    }

    fun reducePoint() {
        score--
    }
    private fun roundOffDecimal(number: Float):Float {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(number).toFloat()
    }
}
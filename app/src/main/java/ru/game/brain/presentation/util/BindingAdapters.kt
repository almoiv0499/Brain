package ru.game.brain.presentation.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import ru.game.brain.R
import ru.game.brain.domain.model.GameResult

@BindingAdapter("requireMinCountRightAnswers")
fun requireMinCountRightAnswers(textView: TextView, minCount: Int) {
    val text = textView.context.getString(R.string.min_count_right_answer)
    setTextToTextView(textView, text, minCount)
}

@BindingAdapter("requireMinPercentRightAnswers")
fun requireMinPercentRightAnswers(textView: TextView, minPercent: Int) {
    val text = textView.context.getString(R.string.min_percent_right_answer)
    setTextToTextView(textView, text, minPercent)
}

@BindingAdapter("requireResultCountRightAnswers")
fun requireResultCountRightAnswers(textView: TextView, score: Int) {
    val text = textView.context.getString(R.string.count_right_answers)
    setTextToTextView(textView, text, score)
}

@BindingAdapter("requireResultPercentRightAnswers")
fun requireResultPercentRightAnswers(textView: TextView, gameResult: GameResult) {
    val text = textView.context.getString(R.string.percent_right_answer)
    setTextToTextView(textView, text, calculateRightAnswersPercent(gameResult))
}

@BindingAdapter("requireImageResult")
fun requireImageResult(imageView: ImageView, isWin: Boolean) {
    imageView.setImageResource(getImageBasedResult(isWin))
}

private fun setTextToTextView(textView: TextView, text: String, count: Int) {
    textView.text = String.format(text, count)
}

private fun calculateRightAnswersPercent(gameResults: GameResult) = with(gameResults) {
    if (countOfQuestions == 0) 0
    else ((countOfRightAnswer / countOfQuestions.toDouble()) * 100).toInt()
}

private fun getImageBasedResult(isWin: Boolean): Int {
    return if (isWin) R.drawable.ic_smiley
    else R.drawable.ic_crying
}
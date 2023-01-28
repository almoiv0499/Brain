package ru.game.brain.presentation.util

import android.content.res.ColorStateList
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.progressindicator.LinearProgressIndicator
import ru.game.brain.R

interface OnClickChooseAnswer {
    fun clickChooseAnswer(option: Int)
}

@BindingAdapter("viewModelEnoughCountRightAnswers")
fun viewModelEnoughCountRightAnswers(textView: TextView, isSuccess: Boolean) {
    textView.setTextColor(getColorRes(textView, isSuccess))
}

@BindingAdapter("viewModelEnoughPercentRightAnswers")
fun viewModelEnoughPercentRightAnswers(indicator: LinearProgressIndicator, isSuccess: Boolean) {
    indicator.progressTintList = ColorStateList.valueOf(getColorRes(indicator, isSuccess))
}

@BindingAdapter("numberToText")
fun numberToText(textView: TextView, count: Int) {
    textView.text = count.toString()
}

@BindingAdapter("onClickChooseAnswer")
fun onClickChooseAnswer(textView: TextView, clickListener: OnClickChooseAnswer) {
    textView.setOnClickListener {
        clickListener.clickChooseAnswer(textView.text.toString().toInt())
    }
}

private fun getColorRes(view: View, success: Boolean): Int {
    val colorResId = if (success) {
        R.color.progress_success_color
    } else {
        R.color.progress_loss_color
    }
    return ContextCompat.getColor(view.context, colorResId)
}
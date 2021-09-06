package com.purwasadr.pantaucovid.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.purwasadr.pantaucovid.util.toDecimalFormat

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("textNegativePositive")
    fun textNegativePositive(textView: TextView, value: Long?) {
        if (value != null) {
            textView.text = if (value > 0) {
                "+" + value.toDecimalFormat()
            } else {
                value.toDecimalFormat()
            }
        }

    }

    @JvmStatic
    @BindingAdapter("textTotalLong")
    fun textTotalLong(textView: TextView, value: Long?) {
        textView.text = value?.toDecimalFormat().orEmpty()
    }
}
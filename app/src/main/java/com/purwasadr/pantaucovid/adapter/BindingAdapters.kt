package com.purwasadr.pantaucovid.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.purwasadr.pantaucovid.util.toDecimalFormat

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:textDecimalFormat")
    fun textDecimalFormat(textView: TextView, value: Int?) {
        textView.text = value?.toDecimalFormat()
    }
}
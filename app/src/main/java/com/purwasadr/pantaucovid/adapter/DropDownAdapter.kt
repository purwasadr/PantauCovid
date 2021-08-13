package com.purwasadr.pantaucovid.adapter

import android.content.Context
import android.widget.ArrayAdapter
import com.purwasadr.pantaucovid.R

class DropDownAdapter(
    context: Context
) : ArrayAdapter<String>(
    context, R.layout.list_item_dropdown, mutableListOf<String>()
) {

    var selectedItemPosition = 0
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    fun submitData(dataList: List<String>) {
        clear()
        addAll(dataList)
    }
}

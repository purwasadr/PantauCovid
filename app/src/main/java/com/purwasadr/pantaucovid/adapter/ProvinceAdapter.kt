package com.purwasadr.pantaucovid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.purwasadr.pantaucovid.databinding.ListItem1Binding

class ProvinceAdapter(
    private val dataList: List<String>
) : RecyclerView.Adapter<ProvinceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListItem1Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = dataList.size

    inner class ViewHolder(
        private val binding: ListItem1Binding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            binding.txt1.text = dataList[position]
        }
    }
}
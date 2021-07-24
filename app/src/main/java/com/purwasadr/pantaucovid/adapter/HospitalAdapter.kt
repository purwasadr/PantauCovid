package com.purwasadr.pantaucovid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.purwasadr.pantaucovid.data.source.local.entity.HospitalEntity
import com.purwasadr.pantaucovid.databinding.ListItemHospitalBinding

class HospitalAdapter(

) : RecyclerView.Adapter<HospitalAdapter.ViewHolder>() {

    private var dataList: MutableList<HospitalEntity> = mutableListOf()

    fun submitList(data: List<HospitalEntity>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ListItemHospitalBinding.inflate(
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
        private val binding: ListItemHospitalBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val hospital = dataList.get(position)

            binding.hospital = hospital
        }
    }
}
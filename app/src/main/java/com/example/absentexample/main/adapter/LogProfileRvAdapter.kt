package com.example.absentexample.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.absentexample.R
import com.example.absentexample.data.LogItem
import kotlinx.android.synthetic.main.item_view_log.view.*

class LogProfileRvAdapter(val context: Context, val items:List<LogItem>) : RecyclerView.Adapter<LogProfileRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view_log, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: LogItem){
            itemView.txt_date.text = item.tanggal
            itemView.txt_time_in.text = item.waktuMasuk
            itemView.txt_time_out.text = item.waktuKeluar
        }
    }
}
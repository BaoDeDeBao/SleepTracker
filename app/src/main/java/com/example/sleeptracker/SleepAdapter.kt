package com.example.sleeptracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class SleepAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<SleepAdapter.SleepViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepViewHolder {
        val itemView = inflater.inflate(R.layout.recycleview_item, parent, false)
        return SleepViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return sleeps.size
    }

    override fun onBindViewHolder(holder: SleepViewHolder, position: Int) {
        val sleepRec = sleeps[position]
        holder.textViewQualityvalue.text = sleepRec.quality.toString()
        holder.textViewStartDate.text = SimpleDateFormat("yyyy.MM.dd.HH:MM")
            .format(sleepRec.startDate.toString())
        holder.textViewEndDate.text = SimpleDateFormat("yyyy.MM.dd.HH:MM")
            .format(sleepRec.endDate.toString())
    }

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var sleeps = emptyList<Sleep>() // Cached copy of words

    inner class SleepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewQualityvalue: TextView = itemView.findViewById(R.id.textViewQualityValue)
        val textViewStartDate: TextView = itemView.findViewById(R.id.textViewStart)
        val textViewEndDate: TextView = itemView.findViewById(R.id.textViewEnd)
    }
}
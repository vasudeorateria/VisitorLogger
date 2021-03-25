package com.kjstudios.visitorlogger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VisitorRvAdapter : RecyclerView.Adapter<VisitorRvAdapter.SecurityViewHolder>() {

    val allVisitors = ArrayList<Visitor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecurityViewHolder {
        val viewHolder = SecurityViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.visitor_item_layout, parent, false)
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: SecurityViewHolder, position: Int) {
        holder.name.text = allVisitors[position].name
    }

    override fun getItemCount(): Int {
        return allVisitors.size
    }

    fun updateVisitors(visitors:List<Visitor>){
        allVisitors.clear()
        allVisitors.addAll(visitors)
        notifyDataSetChanged()
    }

    inner class SecurityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.visitorName)
    }
}


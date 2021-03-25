package com.kjstudios.visitorlogger.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kjstudios.visitorlogger.R
import com.kjstudios.visitorlogger.model.Visitor

class VisitorRvAdapter(private val isAdminPanel: Boolean) :
    ListAdapter<Visitor, VisitorRvAdapter.AdapterViewHolder>(DIFF_UTIL_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val viewHolder = AdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.visitor_item_layout, parent, false)
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val visitor = getItem(position)
        holder.time.text = visitor.time
        holder.name.text = visitor.name
        holder.contact.text = visitor.contact
        holder.concernedPerson.text = visitor.concernedPerson
        holder.purpose.text = visitor.purposeOfVisit
        if (isAdminPanel) {
            holder.address_tv.visibility = View.GONE
            holder.address.visibility = View.GONE
            holder.vehicleNumber_tv.visibility = View.GONE
            holder.vehicleNumber.visibility = View.GONE
        } else {
            holder.address.text = visitor.address
            holder.vehicleNumber.text = visitor.vehicleNumber
        }
    }


    fun getVisitor(position: Int): Visitor {
        return getItem(position)
    }

    inner class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val time: TextView = itemView.findViewById(R.id.visitorTime)
        val name: TextView = itemView.findViewById(R.id.visitorName)
        val contact: TextView = itemView.findViewById(R.id.visitorContact)
        val concernedPerson: TextView = itemView.findViewById(R.id.visitorConcernedPerson)
        val purpose: TextView = itemView.findViewById(R.id.visitorPurpose)
        val address_tv: TextView = itemView.findViewById(R.id.address)
        val address: TextView = itemView.findViewById(R.id.visitorAddress)
        val vehicleNumber_tv: TextView = itemView.findViewById(R.id.vehicleNumber)
        val vehicleNumber: TextView = itemView.findViewById(R.id.visitorVehicleNumber)
    }
}



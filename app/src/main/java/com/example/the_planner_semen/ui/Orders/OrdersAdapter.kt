package com.example.the_planner_semen.ui.Orders
import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.the_planner_semen.R
import com.example.the_planner_semen.data_bd.Order

class OrdersAdapter(
    var items: List<Order>,
    private val context: Context
) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface HideFragmentListener {
        fun onHideFragment()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.id_text_list_item)

        init {
            itemView.setOnClickListener {
                // Вызываем метод интерфейса при нажатии
                vivod(title.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = items[position].nameText
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun vivod(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }
}
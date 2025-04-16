package com.example.the_planner_semen.ui.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.recyclerview.widget.RecyclerView
import com.example.the_planner_semen.R
import com.example.the_planner_semen.data_bd.DialogItemPerson
import com.example.the_planner_semen.data_bd.DialogItemType
import com.example.the_planner_semen.data_bd.SharedViewModel
import com.example.the_planner_semen.ui.dialogs.UniversalDialogPerson


class UniversalAdapter<T>(
    var items: List<T>,
    private val sharedViewModel: SharedViewModel,
    private val context: Context
) : RecyclerView.Adapter<UniversalAdapter<T>.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.id_text_list_item)

        fun bind(item: T) {
            itemView.setOnClickListener {
                if (item is DialogItemPerson) {
                    val clientDialog = UniversalDialogPerson(context,sharedViewModel,1,item as DialogItemPerson)
                    clientDialog.showDialog()
                } else if (item is DialogItemType){
                    //holder.position = position; holder.title.text = (item as DialogItemType).name
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 13)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        // Приведение к конкретному типу данных
        if (item is DialogItemPerson) {
            holder.bind(item)
            holder.title.text = (item as DialogItemPerson).name
        } else if (item is DialogItemType){
            holder.bind(item)
            holder.title.text = (item as DialogItemType).name
        }
}

override fun getItemCount(): Int {
return items.size
}

private fun vivod(s: String) {
Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
}
}
package com.example.test2_bd.recview


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test2_bd.R

import com.example.test2_bd.recview.db_list.ColumnsDataBaseList

val auto_list = ArrayList<ColumnsDataBaseList>()

class AdapterRV: RecyclerView.Adapter<AdapterRV.ViewHolder>(){



    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recview, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleZero.text = auto_list[position].column0
        holder.titleFirst.text = auto_list[position].index.toString()
        holder.titleSecond.text = auto_list[position].column1
        holder.titleThird.text = auto_list[position].column2.toString()
        holder.titleFourth.text = auto_list[position].column3
        holder.titleFifth.text = auto_list[position].column4
    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {

        val titleZero:TextView = view.findViewById(R.id.titleZero)
        val titleFirst:TextView = view.findViewById(R.id.titleFirst)
        val titleSecond:TextView = view.findViewById(R.id.titleSecond)
        val titleThird:TextView = view.findViewById(R.id.titleThird)
        val titleFourth:TextView = view.findViewById(R.id.titleFourth)
        val titleFifth:TextView = view.findViewById(R.id.titleFifth)

    }

    override fun getItemCount(): Int {
        return auto_list.size
    }

}
package com.example.test2_bd

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test2_bd.db.DataBase
import com.example.test2_bd.recview.AdapterRV
import com.example.test2_bd.recview.auto_list

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val adapter = AdapterRV()
    private val dataBase = DataBase(this)
    private val dialog = com.example.test2_bd.dialog.Dialog(this, adapter, dataBase)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBtn()

        recView()

        dataBase.rvList()

    }

    override fun onClick(v: View) {

        when(v.id){
            R.id.delete -> dialog.dialogDelete()

            R.id.allText -> {
                auto_list.clear()
                dataBase.rvList()
                adapter.notifyDataSetChanged()
            }

            R.id.add_auto -> dialog.dial()

            R.id.sortPrice -> {
                auto_list.clear()
                dataBase.sort()
                adapter.notifyDataSetChanged()
            }

            R.id.filter ->{
                val editTextFilter1 = findViewById<EditText>(R.id.edTextFabric)
                val selection1 = editTextFilter1.text.toString()
                val editTextFilter2 = findViewById<EditText>(R.id.edTextType)
                val selection2 = editTextFilter2.text.toString()
                auto_list.clear()
                dataBase.filterManufacturer(selection1,selection2)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun recView(){
        val rvw = findViewById<RecyclerView>(R.id.recView)
        rvw.layoutManager = LinearLayoutManager(this@MainActivity)
        rvw.adapter = adapter

    }
    private fun setBtn(){
        val sort = findViewById<Button>(R.id.add_auto)
        sort.setOnClickListener(this)

        val sort1 = findViewById<Button>(R.id.sortPrice)
        sort1.setOnClickListener(this)

        val filter = findViewById<Button>(R.id.filter)
        filter.setOnClickListener(this)

        val all = findViewById<Button>(R.id.allText)
        all.setOnClickListener(this)

        val del = findViewById<Button>(R.id.delete)
        del.setOnClickListener(this)
    }
}
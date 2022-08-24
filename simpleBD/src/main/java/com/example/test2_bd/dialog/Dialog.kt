package com.example.test2_bd.dialog

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.EditText
import com.example.test2_bd.R
import com.example.test2_bd.db.DataBase
import com.example.test2_bd.recview.AdapterRV
import com.example.test2_bd.recview.auto_list

class Dialog(private val context: Context, private val adapter:AdapterRV, private  val dataBase: DataBase) {

    fun dial(){
        val dial1 = Dialog(context)
        dial1.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dial1.setCancelable(true)
        dial1.setContentView(R.layout.dialog)

        val editTextDialogMark = dial1.findViewById<EditText>(R.id.edTextDialMark)
        val editTextDialogModel = dial1.findViewById<EditText>(R.id.edTextDialModel)
        val editTextDialogPrice = dial1.findViewById<EditText>(R.id.edTextDialPrice)
        val editTextDialogMaxSpeed = dial1.findViewById<EditText>(R.id.edTextDialMaxSpeed)
        val editTextDialogCountry = dial1.findViewById<EditText>(R.id.edTextDialCountry)
        val btnAddCar = dial1.findViewById<Button>(R.id.btn_add)

        btnAddCar.setOnClickListener {
            val mark: String = editTextDialogMark.text.toString()
            val model: String = editTextDialogModel.text.toString()
            val price: String = editTextDialogPrice.text.toString()
            val speed: String = editTextDialogMaxSpeed.text.toString()
            val country: String = editTextDialogCountry.text.toString()

            if ((mark == "") or (model == "") or (price == "") or (speed == "") or (country == ""))
                dial1.dismiss()
            else {
                val edtDialPriceInt = Integer.parseInt(price)
                dataBase.addAuto(
                    mark,
                    model,
                    edtDialPriceInt,
                    speed,
                    country
                )
                adapter.notifyDataSetChanged()
                dial1.dismiss()
            }
        }
        dial1.show()
    }

    fun dialogDelete(){
        val dial1 = Dialog(context)
        dial1.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dial1.setCancelable(true)
        dial1.setContentView(R.layout.dial_delete)

        val editTextDelete = dial1.findViewById<EditText>(R.id.edit_del)
        val btnDeleteCar = dial1.findViewById<Button>(R.id.btn_del)

        btnDeleteCar.setOnClickListener {
            val numb: String = editTextDelete.text.toString()
            if (numb == "")
                dial1.dismiss()
            else {
                val numbInt = Integer.parseInt(numb)
                auto_list.clear()
                dataBase.delete(numbInt)
                dataBase.rvList()
                adapter.notifyDataSetChanged()
                dial1.dismiss()
            }
        }
        dial1.show()
    }

}
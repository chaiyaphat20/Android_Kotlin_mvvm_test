package com.cpfit.examplehiltkotlin.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.cpfit.examplehiltkotlin.R

@SuppressLint("MissingInflatedId")
class CustomDialog(context: Context) : AlertDialog(context) {

    private var txtTitle: TextView
    private var txtDesc: TextView
    private var btnClose: Button

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.detail_product_dialog, null)
        setView(view)

        txtTitle = view.findViewById(R.id.txtTitle)
        txtDesc = view.findViewById(R.id.txtDesc)
        btnClose = view.findViewById(R.id.btnClose)


        btnClose.setOnClickListener {
            dismiss()
        }
    }

    fun setDescription(title: String) {
        txtDesc.text = title
    }

}

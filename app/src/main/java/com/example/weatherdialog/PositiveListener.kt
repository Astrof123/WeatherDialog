package com.example.weatherdialog

import android.content.DialogInterface
import android.util.Log

class PositiveListener: DialogInterface.OnClickListener {
    override fun onClick(dialog: DialogInterface?, choice: Int) {
        Log.d("mytag", "Dialog click $choice")
    }
}
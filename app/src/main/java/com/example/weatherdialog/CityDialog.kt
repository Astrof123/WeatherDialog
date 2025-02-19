package com.example.weatherdialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import java.util.Arrays

class CityDialog(val ctx: Context): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var choice = 0
        return ctx.let { AlertDialog.Builder(it).
            setTitle(R.string.messageText).
            setSingleChoiceItems(ctx.resources.getStringArray(R.array.cities),
                0, activity as DialogInterface.OnClickListener).
            setPositiveButton("Ok",  activity as DialogInterface.OnClickListener).
            create()
        }
    }
}
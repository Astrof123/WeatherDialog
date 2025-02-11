package com.example.weatherdialog

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@SuppressLint("NewApi")
fun getCurrentDateFormatted(): String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru")) // Используем Locale для русского языка
    return currentDate.format(formatter)
}

class MainActivity : AppCompatActivity(), DialogInterface.OnClickListener {
    private lateinit var fragmentManager: FragmentManager
    public var currentFragment = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager = supportFragmentManager

        val city_option: Button = findViewById(R.id.city_option)

        city_option.setOnClickListener {
            CityDialog(this).show(supportFragmentManager, "test")
        }
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            when (currentFragment){
                0 -> showFragment(ShelehovFragment());
                1 -> showFragment(IrkutskFragment());
                2 -> showFragment(AngarskFragment());
            }
        }

        when (which){
            0 -> currentFragment = which;
            1 -> currentFragment = which;
            2 -> currentFragment = which;
        }
    }

    private fun showFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}
package com.example.weatherdialog

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import android.os.LocaleList
import android.view.Menu
import android.view.MenuItem


@SuppressLint("NewApi")
fun getCurrentDateFormatted(): String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))
    return currentDate.format(formatter)
}


class MainActivity : AppCompatActivity(), DialogInterface.OnClickListener {
    private fun loadLocale() {
        val sharedPref = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val localeToSet: String = sharedPref.getString("locale_to_set", "")!!
        setLocale(localeToSet)
    }

    private fun setLocale(localeToSet: String) {
        val localeListToSet = LocaleList(Locale(localeToSet))
        LocaleList.setDefault(localeListToSet)
        resources.configuration.setLocales(localeListToSet)
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)
        val sharedPref = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        sharedPref.putString("locale_to_set", localeToSet)
        sharedPref.apply()


    }

    private lateinit var fragmentManager: FragmentManager
    var currentFragment = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocale()
        setContentView(R.layout.activity_main)
        fragmentManager = supportFragmentManager

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val city_option: Button = findViewById(R.id.city_option)

        city_option.setOnClickListener {
            CityDialog(this).show(supportFragmentManager, "test")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_english -> {
                setLocale("en")
                recreate()
                true
            }
            R.id.action_chinese -> {
                setLocale("zh")
                recreate()
                true
            }

            else -> {
                setLocale("en")
                recreate()
                true
            }
        }
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            val bundle = Bundle()

            when (currentFragment){
                0 -> {
                    bundle.putString("city", "Shelekhov")
                    val shelekhov = WeatherFragment()
                    shelekhov.arguments = bundle
                    showFragment(shelekhov);
                }
                1 -> {
                    bundle.putString("city", "Irkutsk")
                    val irkutsk = WeatherFragment()
                    irkutsk.arguments = bundle
                    showFragment(irkutsk);
                };
                2 -> {
                    bundle.putString("city", "Angarsk")
                    val angarsk = WeatherFragment()
                    angarsk.arguments = bundle
                    showFragment(angarsk);
                };
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
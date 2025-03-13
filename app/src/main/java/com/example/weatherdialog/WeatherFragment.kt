package com.example.weatherdialog

import RetrofitClient
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.weatherdialog.databinding.FragmentWeatherBinding
import com.google.android.flexbox.FlexboxLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WeatherFragment : Fragment() {
    private lateinit var progressBar: ProgressBar
    private lateinit var leftWrapper: LinearLayout
    private lateinit var rightWrapper: FlexboxLayout
    private lateinit var bottomWrapper: FlexboxLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentWeatherBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)

        lifecycleScope.launch {
            try {
                var vision = Vision(
                    View.VISIBLE,
                    View.GONE
                )

                binding.vision = vision

                val API_KEY = "24664d677196183d8f6a0d12e063a503"

                val weather = withContext(Dispatchers.IO) {
                    RetrofitClient.apiService.getWeather(apiKey = API_KEY, city = arguments?.getString("city") ?: "Irkutsk")
                }
                Log.d("Retrofit", "Weather: $weather")

                val weather_iconn: Int
                if (weather.weather[0].main == "Clouds") {
                    weather_iconn = R.drawable.cloud
                }
                else if (weather.weather[0].main == "Clear") {
                    weather_iconn = R.drawable.sunny
                }
                else {
                    weather_iconn = R.drawable.cloudy
                }

                val weatherData = WeatherFragmentData(
                    arguments?.getString("city") ?: "Irkutsk",
                    getCurrentDateFormatted(),
                    weather_icon = weather_iconn,
                    day_temp = weather.main.temp.toString() + "°",
                    feels_like = weather.main.temp.toString() + "°",
                    pressure = weather.main.pressure.toString(),
                    wind_speed = weather.wind.speed.toString() + " м/c",
                    humidity = weather.main.humidity.toString() + "%",
                )

                binding.weather = weatherData

                vision = Vision(
                    View.GONE,
                    View.VISIBLE
                )

                binding.vision = vision

            } catch (e: Exception) {
                Log.e("Retrofit", "Error: ${e.message}")
            }
        }


        return binding.root
    }

}
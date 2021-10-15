package com.example.git

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.git.databinding.ActivityMainBinding
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import org.w3c.dom.Text
import java.net.URL

class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding

    var text:TextView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        text = findViewById(R.id.text12312)


        binding.button.setOnClickListener {

            val url:String = "https://api.openweathermap.org/data/2.5/weather?q=Samara&appid=4c8985af24857262f0a789c070acfe70&units=metric&lang=ru"



            doAsync {

                val apiResponse = URL(url).readText()


                 val weather = JSONObject(apiResponse).getJSONArray("weather")
                 val desc = weather.getJSONObject(0).getString("description")

                 val main = JSONObject(apiResponse).getJSONObject("main")
                 val temp = main.getString("temp")

                text?.text = "$temp \n $desc"

                Log.d("INFO", apiResponse)
            }
        }



    }
}
package com.hong_dev.movierank

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.hong_dev.movierank.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        setContentView(binding.root)

        binding.tvToday.text = SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault()).format(Calendar.getInstance().time)

        val cal: Calendar = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH, -1)
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val targetDt = dateFormat.format(cal.time)


        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.kobis.or.kr")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: RetrofitService = retrofit.create(RetrofitService::class.java)
        val call: Call<MovieResponse> = service.getBoxOffice("e53b7f88ed7c3e955e30d8e5ed764752", targetDt)

        call.enqueue(object  : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {

                    var movieResponse: MovieResponse? = response.body()
                    var dailyBoxOfficeList = movieResponse?.boxOfficeResult?.dailyBoxOfficeList

                    binding.recyclerview.adapter=MyAdapter(this@MainActivity, dailyBoxOfficeList!!)



            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"실패", Toast.LENGTH_SHORT).show()
            }

        })


    }
}
package com.hong_dev.movierank

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.hong_dev.movierank.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val now = LocalDateTime.now()
        val formatDate = DateTimeFormatter.ISO_DATE
        val nowDate = now.format(formatDate)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.kobis.or.kr")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: RetrofitService = retrofit.create(RetrofitService::class.java)

        val call: Call<MovieResponse> = service.getBoxOffice("e53b7f88ed7c3e955e30d8e5ed764752", "20230705")

        call.enqueue(object  : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if(response.isSuccessful){
                    val movieResponse = response.body()
                    val dailyBoxOfficeList = movieResponse?.boxOfficeResult?.dailyBoxOfficeList

                    binding.recyclerView.adapter = MyAdapter(this@MainActivity, dailyBoxOfficeList)

                }

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"실패", Toast.LENGTH_SHORT).show()
            }

        })


    }
}
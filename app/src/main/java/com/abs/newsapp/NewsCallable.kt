package com.abs.newsapp

import retrofit2.Call
import retrofit2.http.GET

interface NewsCallable {
    @GET("/v2/top-headlines?country=us&category=general&apiKey=ac0ed1c3902b45f48264e236fe79fe21&pageSize=30")
    fun getNews(): Call<News>
}
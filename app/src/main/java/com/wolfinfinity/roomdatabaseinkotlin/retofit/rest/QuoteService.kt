package com.wolfinfinity.roomdatabaseinkotlin.retofit.rest

import com.wolfinfinity.roomdatabaseinkotlin.retofit.model.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    @GET("quotes?")
    suspend fun getQuoteList(@Query("page1") page1: Int): Response<QuoteList>
}
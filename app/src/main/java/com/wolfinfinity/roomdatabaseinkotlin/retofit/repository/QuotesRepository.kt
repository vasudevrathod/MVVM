package com.wolfinfinity.roomdatabaseinkotlin.retofit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wolfinfinity.roomdatabaseinkotlin.retofit.model.QuoteList
import com.wolfinfinity.roomdatabaseinkotlin.retofit.rest.QuoteService

class QuotesRepository(private val quoteService: QuoteService) {

    //private val quoteListData = MutableLiveData<QuoteList>()
    private val quoteListData = MutableLiveData<Response<QuoteList>>()

    val quotes: LiveData<Response<QuoteList>>
    get() = quoteListData

    suspend fun getQuotes(page: Int) {
        try {
            val result = quoteService.getQuoteList(page)
            if (result?.body() != null) {
                quoteListData.postValue(Response.Success(result.body()))
            }
        } catch (e: Exception) {
            quoteListData.postValue(Response.Error(e.message.toString()))
        }

    }
}
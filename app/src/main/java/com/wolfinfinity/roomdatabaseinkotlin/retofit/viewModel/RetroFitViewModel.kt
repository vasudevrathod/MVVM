package com.wolfinfinity.roomdatabaseinkotlin.retofit.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wolfinfinity.roomdatabaseinkotlin.retofit.model.QuoteList
import com.wolfinfinity.roomdatabaseinkotlin.retofit.repository.QuotesRepository
import com.wolfinfinity.roomdatabaseinkotlin.retofit.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RetroFitViewModel(private val repository: QuotesRepository): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)
        }
    }

    val quotes: LiveData<Response<QuoteList>>
        get() = repository.quotes
}
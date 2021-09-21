package com.wolfinfinity.roomdatabaseinkotlin.retofit.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wolfinfinity.roomdatabaseinkotlin.retofit.repository.QuotesRepository

class RetrofitViewModelFactory(private val repository: QuotesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RetroFitViewModel(repository) as T
    }
}
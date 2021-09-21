package com.wolfinfinity.roomdatabaseinkotlin.retofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wolfinfinity.roomdatabaseinkotlin.R
import com.wolfinfinity.roomdatabaseinkotlin.retofit.repository.QuotesRepository
import com.wolfinfinity.roomdatabaseinkotlin.retofit.repository.Response
import com.wolfinfinity.roomdatabaseinkotlin.retofit.rest.QuoteService
import com.wolfinfinity.roomdatabaseinkotlin.retofit.rest.RetrofitHelper
import com.wolfinfinity.roomdatabaseinkotlin.retofit.viewModel.RetroFitViewModel
import com.wolfinfinity.roomdatabaseinkotlin.retofit.viewModel.RetrofitViewModelFactory

class MainNewActivity : AppCompatActivity() {

    private lateinit var retroFitViewModel: RetroFitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new)

        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val repository = QuotesRepository(quoteService)
        retroFitViewModel = ViewModelProvider(this, RetrofitViewModelFactory(repository)).get(RetroFitViewModel::class.java)

        retroFitViewModel.quotes.observe(this, Observer {
            when(it) {
                is Response.Loading -> {}
                is Response.Success -> {
                    it.data?.let {
                        Toast.makeText(this, it.results.size.toString(), Toast.LENGTH_LONG).show()
                        Log.w("VASUDEV", it.results.toString())
                    }
                }
                is Response.Error -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                }
            }

        })
    }
}
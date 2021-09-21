package com.wolfinfinity.roomdatabaseinkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.wolfinfinity.roomdatabaseinkotlin.retofit.MainNewActivity
import com.wolfinfinity.roomdatabasekotlin.mvvm.ContactRepository
import com.wolfinfinity.roomdatabasekotlin.mvvm.MainViewModel
import com.wolfinfinity.roomdatabasekotlin.mvvm.MainViewModelFactory
import com.wolfinfinity.roomdatabasekotlin.simpleDemo.Contact
import com.wolfinfinity.roomdatabasekotlin.simpleDemo.ContactDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var database: ContactDatabase
    private lateinit var mainViewModel: MainViewModel
    private lateinit var btGetData: Button
    private lateinit var btRetroFit: Button
    private lateinit var tvData: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btGetData = findViewById(R.id.btGetData)
        btRetroFit = findViewById(R.id.btRetroFit)
        tvData = findViewById(R.id.tvData)

        database = ContactDatabase.getDatabase(this)

        val dao = ContactDatabase.getDatabase(this).contactDao()
        val repository = ContactRepository(dao)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)



        mainViewModel.getContact().observe(this, {
            tvData.text = it.toString()
        })

        clickListener()
    }

    private fun clickListener() {
        btGetData.setOnClickListener(this)
        btRetroFit.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.btGetData -> {
                GlobalScope.launch {
                    database.contactDao().insertContact(Contact(0, "SD", "1", Date(), 1))
                }
                /*database.contactDao().getContact().observe(this, {
                    Log.w("Vasudev", it.toString())
                })*/
            }
            R.id.btRetroFit -> {
                startActivity(Intent(this, MainNewActivity::class.java))
            }
        }
    }
}
package com.wolfinfinity.roomdatabasekotlin.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wolfinfinity.roomdatabasekotlin.simpleDemo.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val contactRepository: ContactRepository): ViewModel() {
    fun getContact(): LiveData<List<Contact>> {
        return contactRepository.getContact()
    }

    fun insertContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.insertContact(contact)
        }
    }
}
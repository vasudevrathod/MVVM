package com.wolfinfinity.roomdatabasekotlin.mvvm

import androidx.lifecycle.LiveData
import com.wolfinfinity.roomdatabasekotlin.simpleDemo.Contact
import com.wolfinfinity.roomdatabasekotlin.simpleDemo.ContactDao

class ContactRepository (private val contactDao: ContactDao) {

    fun getContact(): LiveData<List<Contact>> {
        return contactDao.getContact()
    }

    suspend fun insertContact(contact: Contact) {
        contactDao.insertContact(contact)
    }
}
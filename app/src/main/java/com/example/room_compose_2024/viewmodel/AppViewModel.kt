package com.example.room_compose_2024.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room_compose_2024.ItemApplication
import com.example.room_compose_2024.data.Item
import com.example.room_compose_2024.data.ItemsRepository
import com.example.room_compose_2024.data.ItemsRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val itemDao = (application as ItemApplication).database.itemDao()

    private val repository: ItemsRepository = ItemsRepositoryImpl(itemDao)

    val allItems: Flow<List<Item>> = repository.getAllItemsStream()

    // Function to get a specific item by id
    fun getItem(id: Int): Flow<Item?> {
        return repository.getItemStream(id)
    }

    // Function to insert a new item
    fun insertItem(item: Item) = viewModelScope.launch {
        repository.insertItem(item)
    }

    // Function to update an existing item
    fun updateItem(item: Item) = viewModelScope.launch {
        repository.updateItem(item)
    }

    // Function to delete an item
    fun deleteItem(item: Item) = viewModelScope.launch {
        repository.deleteItem(item)
    }
}
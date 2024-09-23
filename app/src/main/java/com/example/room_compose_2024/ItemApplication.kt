package com.example.room_compose_2024

import android.app.Application
import androidx.room.Room
import com.example.room_compose_2024.data.InventoryDatabase

class ItemApplication : Application() {

    val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            InventoryDatabase::class.java,
            "inventory_db"
        ).build()
    }
}
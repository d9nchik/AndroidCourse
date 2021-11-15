package com.example.androidcourse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Order::class)], version = 1)
abstract class OrderRoomDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao

    companion object {
        private var INSTANCE: OrderRoomDatabase? = null

        internal fun getDatabase(context: Context): OrderRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(OrderRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            OrderRoomDatabase::class.java,
                            "order_database"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
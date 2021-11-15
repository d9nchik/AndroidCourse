package com.example.androidcourse

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "orders")
class Order {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "order_owner")
    @NotNull
    var orderOwner: String = ""

    @ColumnInfo(name = "color")
    @NotNull
    var color: String = ""

    @ColumnInfo(name = "price")
    @NotNull
    var price: String = ""

    constructor()

    constructor(id: Int, orderOwner: String, color: String, price: String) {
        this.id = id
        this.orderOwner = orderOwner
        this.color = color
        this.price = price
    }

    constructor(orderOwner: String, color: String, price: String) {
        this.orderOwner = orderOwner
        this.color = color
        this.price = price
    }
}
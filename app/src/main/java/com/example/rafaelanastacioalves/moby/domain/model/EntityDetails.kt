package com.example.rafaelanastacioalves.moby.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class EntityDetails {

    @PrimaryKey
    @ColumnInfo(name = "title")
    lateinit var title: String
    @ColumnInfo(name = "description")
    lateinit var description: String
    @ColumnInfo(name = "price")
    lateinit var price: String
    @ColumnInfo(name = "image_url")
    @SerializedName("image_url")
    lateinit var imageUrl: String

}

package com.example.rafaelanastacioalves.moby.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rafaelanastacioalves.moby.domain.model.MainEntity


@Database(entities = [MainEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun appDAO(): DAO
}

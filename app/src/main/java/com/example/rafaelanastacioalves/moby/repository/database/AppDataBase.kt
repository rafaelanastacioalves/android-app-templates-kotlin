package com.example.rafaelanastacioalves.moby.repository.database;

import android.content.Context
import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase;
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;


@Database(entities = [MainEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun appDAO(): DAO
}

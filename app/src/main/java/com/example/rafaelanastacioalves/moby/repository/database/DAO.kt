package com.example.rafaelanastacioalves.moby.repository.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.rafaelanastacioalves.moby.domain.model.MainEntity

@Dao
interface DAO {
    @Query("SELECT * FROM mainentity")
    fun getMainEntityList(): List<MainEntity>

    @Insert
    fun saveMainEntityList(resultData: List<MainEntity>?)

    @Delete
    fun delete(mainEntity: MainEntity)

    @Query("DELETE FROM mainentity")
    fun delteAllMainEntities()
}
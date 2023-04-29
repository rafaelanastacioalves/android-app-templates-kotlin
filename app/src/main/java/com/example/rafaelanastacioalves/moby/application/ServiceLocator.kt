package com.example.rafaelanastacioalves.moby.application

import android.content.Context
import androidx.room.Room
import com.example.rafaelanastacioalves.moby.repository.AppRepository
import com.example.rafaelanastacioalves.moby.repository.database.AppDataBase
import com.example.rafaelanastacioalves.moby.repository.database.DAO
import com.example.rafaelanastacioalves.moby.repository.http.APIClient
import com.example.rafaelanastacioalves.moby.repository.http.ServiceGenerator

object ServiceLocator {

    private var database : AppDataBase? = null
    @Volatile
    private var appRepository: AppRepository? = null

    fun provideAppRespository(context: Context) : AppRepository {
        synchronized(this) {
            return appRepository ?: createAppRepository(context)
        }
    }

    private fun createAppRepository(context: Context) : AppRepository {
        return AppRepository(createAppDao(context),createApiClient(context))
    }

    private fun createAppDao(context: Context): DAO {
        return createDatabase(context).appDAO()
    }

    private fun createApiClient(context: Context): APIClient {
        return ServiceGenerator.createService(APIClient::class.java)
    }

    //create database
    fun createDatabase(context: Context): AppDataBase {
        val result = buildDatabase(context)
        database = result
        return result

    }

    const val databaseName : String = "applicationDB"

    private fun buildDatabase(context: Context): AppDataBase {
        return Room.databaseBuilder(context.applicationContext,
            AppDataBase::class.java,
            databaseName).build()
    }
}
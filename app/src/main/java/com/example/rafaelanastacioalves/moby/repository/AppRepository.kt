package com.example.rafaelanastacioalves.moby.repository

import android.util.Log
import com.example.rafaelanastacioalves.moby.domain.model.EntityDetails
import com.example.rafaelanastacioalves.moby.domain.model.MainEntity
import com.example.rafaelanastacioalves.moby.domain.model.Resource
import com.example.rafaelanastacioalves.moby.repository.database.DAO
import com.example.rafaelanastacioalves.moby.repository.http.APIClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class AppRepository(
    private val appDao: DAO,
    var apiClient: APIClient) {

    suspend fun mainEntity(): Flow<Resource<List<MainEntity>>> {

        return flow {
            val finalList: MutableList<MainEntity> = ArrayList<MainEntity>()
            var number = 0
            while (true) {
                delay(2000)
                number++
                Log.d(javaClass.name, "emitting in flow a number value $number")
                finalList.add(0,
                    MainEntity("$number","Entity number $number", "price", "reais", "url")
                )

                emit(
                    Resource.success(finalList)
                )

            }
        }

    }

    suspend fun mainEntityAdditional(): Resource<List<MainEntity>> {
        return object : NetworkBoundResource<List<MainEntity>, List<MainEntity>>() {
            override suspend fun fecthFromHttp(): List<MainEntity> {
                return apiClient.getMainEntityListAdditional()
            }

            override suspend fun getFromDB(): List<MainEntity>? {
                TODO("Not yet implemented")
            }

            override fun saveIntoDB(resultData: List<MainEntity>?) {
                TODO("Not yet implemented")
            }

        }.fromHttpOnly()
    }

    suspend fun entityDetails(requestId: String): Resource<EntityDetails> {
        return object : NetworkBoundResource<EntityDetails, EntityDetails>() {
            override suspend fun fecthFromHttp(): EntityDetails {
                return apiClient.getEntityDetails(requestId)
            }

            override suspend fun getFromDB(): EntityDetails? {
                TODO("Not yet implemented")
            }

            override fun saveIntoDB(resultData: EntityDetails?) {
                TODO("Not yet implemented")
            }
        }.fromHttpOnly()
    }
}
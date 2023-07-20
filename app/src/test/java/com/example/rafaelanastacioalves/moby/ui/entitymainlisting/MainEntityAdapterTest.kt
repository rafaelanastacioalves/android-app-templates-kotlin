package com.example.rafaelanastacioalves.moby.ui.entitymainlisting

import android.content.Context
import com.example.rafaelanastacioalves.moby.domain.model.MainEntity
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import kotlin.collections.ArrayList


@RunWith(MockitoJUnitRunner::class)
class MainEntityAdapterTest {

    private val context : Context = Mockito.mock(Context::class.java)
    private var adapter: MainEntityAdapter = Mockito.spy(MainEntityAdapter(context))

    @Test
    open fun should_UpdateMainEntityList_WhenSetItemList(): Unit {
        doNothing().`when`(adapter).updateList()
        val mockedMainEntityList: List<MainEntity> = ArrayList(Arrays.asList(
                MainEntity(
                        "Entity 1",
                        "title",
                        "price",
                        "dolar",
                        "http://"
                ),
                MainEntity(
                        "Entity 2",
                        "title",
                        "price",
                        "dolar",
                        "http://"
                )
        ))
        adapter.setItems(mockedMainEntityList)
        verify(adapter).updateList()
        val itemCount = adapter.itemCount
        assertThat(itemCount, `is`(2))
    }





}
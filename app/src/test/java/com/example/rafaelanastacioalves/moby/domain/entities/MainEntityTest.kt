package com.example.rafaelanastacioalves.moby.domain.entities

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class MainEntityTest {

    @Test
    fun should_giveBasicProperties_WhenMainEntityCreatedOnlyWithTitle() {

        var testedMainEntitty: MainEntity = createMainEntity("1")
        assertThat(testedMainEntitty.id, `is`(equalTo("1")))
        assertThat(testedMainEntitty.title, `is`(equalTo("title")))
        assertThat(testedMainEntitty.imageUrl, `is`(equalTo("http://")))
    }

    fun createMainEntity(id: String): MainEntity {
        return MainEntity(
                id,
                "title",
                "price",
                "dolar",
                "http://"
        )
    }
}
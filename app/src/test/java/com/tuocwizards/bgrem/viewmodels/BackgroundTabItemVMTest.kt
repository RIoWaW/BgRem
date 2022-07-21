package com.tuocwizards.bgrem.viewmodels

import com.tuocwizards.bgrem.models.datastorages.storages.BackgroundStorage
import com.tuocwizards.bgrem.models.repositories.exchange.background.BackgroundItem
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

internal class BackgroundTabItemVMTest {

    private val storage = mockk<BackgroundStorage>()

    @BeforeEach
    fun prepareData() {
        every { storage.backgrounds } returns mutableListOf(
            BackgroundItem(null, "color",
                null, null, null, null),
            BackgroundItem(null, "color",
                null, null, null, null),
            BackgroundItem(null, "image",
                null, null, null, null),
            BackgroundItem(null, "video",
                null, null, null, null),
            BackgroundItem(null, "user",
                null, null, null, null),
            BackgroundItem(null, "image",
                null, null, null, null)
        )
    }

    @Test
    fun getColorBackground() {
        //preparing
        val expected = 2
        val viewmodel = BackgroundTabItemVM()
        //action
        val actual = viewmodel.getColorBackground().size
        //check
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun getPictureBackground() {
        //preparing
        val expected = 3
        val viewmodel = BackgroundTabItemVM()
        //action
        val actual = viewmodel.getPictureBackground().size
        //check
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun getUserBackground() {
        //preparing
        val expected = 1
        val viewmodel = BackgroundTabItemVM()
        //action
        val actual = viewmodel.getUserBackground().size
        //check
        Assertions.assertEquals(expected, actual)
    }
}
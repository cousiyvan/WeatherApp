package com.learning.cousiyvan.test

import com.learning.cousiyvan.weatherapp.extensions.toDateString
import org.junit.Assert
import org.junit.Test
import java.text.DateFormat

class ExtensionsTest {
    @Test
    fun `"longToDateString" returns valid value`() {
        Assert.assertEquals("Oct 19, 2015", 1445275635000L.toDateString())
    }

    @Test
    fun `"longToDateString" with full format returns valid value`() {
        Assert.assertEquals("Monday, October 19, 2015", 1445275635000L.toDateString(DateFormat.FULL))
    }
}
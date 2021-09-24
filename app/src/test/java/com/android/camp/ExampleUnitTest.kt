package com.android.camp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun inteCevir_isCorrect() {
        // başarılı senaryo kontrolü
        assertTrue("deneme".inteCevir() == 0)
        assertTrue("123".inteCevir() == 123)
        assertTrue("10".inteCevir() == 10)

        // başarısız senarya kontrolü
        assertFalse("deneme".inteCevir() == 1)
        assertFalse("123".inteCevir() == 0)
        assertFalse("10".inteCevir() == 0)
    }
}
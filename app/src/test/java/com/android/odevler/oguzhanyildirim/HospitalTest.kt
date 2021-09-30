package com.android.odevler.oguzhanyildirim

import com.android.odevler.oguzhanyildirim.data.model.Hospital
import org.junit.Assert
import org.junit.Test

class HospitalTest {

    private val hospital = Hospital(
        name = "Ekol Hastanesi",
        contactNumber = "(0232) 386 55 05",
        capacity = "170",
        address = "Ataşehir, 8019/16. Sk., 35630 Çiğli/İzmir"
    )

    @Test
    fun testIncreaseCapacity(){
        val previousCapacity = hospital.capacity.toInt()
        hospital.increaseCapacity(1)

        // Success
        Assert.assertFalse(hospital.capacity.toInt() != previousCapacity + 1)
        Assert.assertFalse(hospital.capacity.toInt() > Int.MAX_VALUE)
        Assert.assertFalse(hospital.capacity.toInt() < 0)

        // Failure
        Assert.assertTrue(hospital.capacity.toInt() == previousCapacity + 1)
    }

    @Test
    fun isValidHospital(){
        // Success
        Assert.assertTrue(hospital.name.isNotEmpty())

        // Failure
        Assert.assertFalse(hospital.name.isEmpty())
        Assert.assertFalse(hospital.capacity.toInt() < 0)

    }

}
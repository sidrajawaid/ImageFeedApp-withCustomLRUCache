package com.example.imagefeedapp

import android.graphics.Bitmap
import com.example.imagefeedapp.data.cache.LRUCache
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.jvm.java
import org.mockito.kotlin.whenever
import org.mockito.Mockito.mock
import org.mockito.internal.matchers.Null
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToLong


class LRUCacheTest {

    private lateinit var mockBitmap7: Bitmap
    private lateinit var mockBitmap6: Bitmap
    private lateinit var mockBitmap5: Bitmap
    private lateinit var mockBitmap4: Bitmap
    var cache: LRUCache? = null
    private lateinit var mockBitmap1: Bitmap
    private lateinit var mockBitmap2: Bitmap
    private lateinit var mockBitmap3: Bitmap

    @Before
    fun setUp() {
        cache = LRUCache(3000) // fresh cache before each test

        mockBitmap1 = mock(Bitmap::class.java)
        mockBitmap2 = mock(Bitmap::class.java)
        mockBitmap3 = mock(Bitmap::class.java)

        whenever(mockBitmap1.byteCount).thenReturn(1000)
        whenever(mockBitmap2.byteCount).thenReturn(500)
        whenever(mockBitmap3.byteCount).thenReturn(1500)

    }

    @Test
    fun testReturnsCorrectBitmap() {
        cache?.addUrlEntry(
            "url/1",
            mockBitmap1
        )
        cache?.addUrlEntry(
            "url/2",
            mockBitmap2
        )
        cache?.addUrlEntry(
            "url/3",
            mockBitmap3
        )

        assertEquals(1000, cache?.getBitmap("url/1")?.byteCount)
    }

    @Test
    fun testReturnNullForBitmapNotInserted() {
        assertNull(cache?.getBitmap("url/5")?.byteCount)
    }

    @Test
    fun testPerformsEvictionForNewBitmapEntry() {

        mockBitmap4 = mock(Bitmap::class.java)
        whenever(mockBitmap4.byteCount).thenReturn(1000)

        cache?.addUrlEntry(
            "https://picsum.photos/id/1084/536/354?grayscale",
            mockBitmap4
        )
        assertEquals(null, cache?.getBitmap("url/1")?.byteCount)

    }

    @Test
    fun testReturnsSecondOccurrenceOfSameUrl() {

        mockBitmap5 = mock(Bitmap::class.java)
        whenever(mockBitmap5.byteCount).thenReturn(500)

        mockBitmap6 = mock(Bitmap::class.java)
        whenever(mockBitmap6.byteCount).thenReturn(600)

        cache?.addUrlEntry(
            "url/6",
            mockBitmap5
        )
        cache?.addUrlEntry(
            "url/6",
            mockBitmap6
        )
        assertEquals(600, cache?.getBitmap("url/6")?.byteCount)

    }

    @Test
    fun testReturnNullForLargeBitmap() {
        mockBitmap7 = mock(Bitmap::class.java)
        whenever(mockBitmap7.byteCount).thenReturn(5000)

        cache?.addUrlEntry(
            "url/7",
            mockBitmap7
        )
        assertNull(cache!!.getBitmap("url/7")?.byteCount)
    }

    @Test
    fun testForEmptyCache() {
        cache!!.addUrlEntry("url/1", mockBitmap1)
        cache!!.addUrlEntry("url/2", mockBitmap2)
        cache!!.addUrlEntry("url/3", mockBitmap3)
        // currentSize is now 3000, three entries in cache

        cache!!.clear()
        assertNull(cache!!.getBitmap("url/1"))
        assertNull(cache!!.getBitmap("url/2"))
        assertNull(cache!!.getBitmap("url/3"))
        assertEquals(0, cache!!.getCacheStats().currentCacheSize)
    }


}
package com.example.imagefeedapp

import android.graphics.Bitmap
import com.example.imagefeedapp.data.cache.LRUCache
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.jvm.java
import org.mockito.kotlin.whenever
import org.mockito.Mockito.mock
import java.math.BigDecimal
import java.math.RoundingMode


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

    //size tracking

    @Test
    fun `currentSize increases by bitmap byteCount after put`() {
        cache!!.addUrlEntry("url/1", bitmap = mockBitmap1)
        val stats = cache!!.getCacheStats()
        assertEquals(1000, stats.currentCacheSize)
    }

    @Test
    fun `currentSize decreases by bitmap byteCount after eviction`() {
        cache!!.addUrlEntry("url/1", bitmap = mockBitmap1)
        cache!!.addUrlEntry("url/2", bitmap = mockBitmap2)
        cache!!.addUrlEntry("url/3", bitmap = mockBitmap3)
        //cache size = 3000
        mockBitmap4 = mock(Bitmap::class.java)
        whenever(mockBitmap4.byteCount).thenReturn(100)
        cache!!.addUrlEntry("url/4", mockBitmap4)
        val stats = cache!!.getCacheStats()
        assertEquals(2100, stats.currentCacheSize)
    }

    @Test
    fun testForCurrentSizeZeroOnFreshCache() {

        val stats = cache!!.getCacheStats()
        assertEquals(0, stats.currentCacheSize)

    }

    @Test
    fun testShowsBitmapLargerThanMaxSizeIsNotInserted() {
        val mockBitmap4 = mock(Bitmap::class.java)
        whenever(mockBitmap4.byteCount).thenReturn(5000)
        cache!!.addUrlEntry("url/1", mockBitmap4)
        assertNull(cache!!.getBitmap("url/1"))
    }

    @Test
    fun testShowsBitmapExactlyEqualToMaxSizeIsInsertedSuccessfully() {
        val mockBitmap4 = mock(Bitmap::class.java)
        whenever(mockBitmap4.byteCount).thenReturn(3000)
        cache!!.addUrlEntry("url/4", mockBitmap4)
        assertEquals(3000, cache!!.getBitmap("url/4")!!.byteCount)
    }

    @Test
    fun testShowsCurrentSizeNeverExceedsMaxSizeAfterMultipleAdditions() {
// fill cache completely
        cache!!.addUrlEntry("url/1", mockBitmap1)  // 1000
        cache!!.addUrlEntry("url/2", mockBitmap2)  // 500
        cache!!.addUrlEntry("url/3", mockBitmap3)  // 1500
        // currentSize = 3000 = maxSize

        // keep inserting — evictions should keep currentSize within bounds
        val extraBitmap1 = mock(Bitmap::class.java)
        val extraBitmap2 = mock(Bitmap::class.java)
        val extraBitmap3 = mock(Bitmap::class.java)
        whenever(extraBitmap1.byteCount).thenReturn(800)
        whenever(extraBitmap2.byteCount).thenReturn(1200)
        whenever(extraBitmap3.byteCount).thenReturn(600)

        cache!!.addUrlEntry("url/4", extraBitmap1)
        cache!!.addUrlEntry("url/5", extraBitmap2)
        cache!!.addUrlEntry("url/6", extraBitmap3)

        val stats = cache!!.getCacheStats()
        assertTrue(stats.currentCacheSize <= stats.maxSize)
    }

    @Test
    fun testPerformsRecentlyAccessedEntrySurvivesEvictionOverUnaccessedEntry() {
        cache!!.addUrlEntry("url/1", mockBitmap1)  // 1000 - inserted first
        cache!!.addUrlEntry("url/2", mockBitmap2)  // 500
        cache!!.addUrlEntry("url/3", mockBitmap3)  // 1500
        // cache full at 3000

        cache!!.getBitmap("url/1")  // access url/1 — promotes it to MRU

        // insert new entry — forces eviction
        // url/2 should be evicted (LRU), not url/1 (recently accessed)
        val newBitmap = mock(Bitmap::class.java)
        whenever(newBitmap.byteCount).thenReturn(500)
        cache!!.addUrlEntry("url/4", newBitmap)

        assertNull(cache!!.getBitmap("url/2"))       // evicted — was LRU
        assertNotNull(cache!!.getBitmap("url/1"))     // survived — was accessed
        assertNotNull(cache!!.getBitmap("url/3"))     // survived — inserted after url/2
        assertNotNull(cache!!.getBitmap("url/4"))     // just inserted
    }

    @Test
    fun testReturnsCorrectHitRate() {
        // specific byteCount needed only here — declare inside
        val smallBitmap = mock(Bitmap::class.java)
        whenever(smallBitmap.byteCount).thenReturn(100)

        cache!!.addUrlEntry("url/small", smallBitmap)
        cache!!.getBitmap("url/small")   // hit
        cache!!.getBitmap("url/small")   // hit
        cache!!.getBitmap("url/missing") // miss

        val stats = cache!!.getCacheStats()
        val temp = BigDecimal(stats.hitRate.toString()).setScale(2, RoundingMode.HALF_UP).toFloat()

        assertEquals(0.67f, temp)
    }

    //


}
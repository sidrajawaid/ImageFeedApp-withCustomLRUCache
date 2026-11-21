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
            mockBitmap1,
            1
        )
        cache?.addUrlEntry(
            "url/2",
            mockBitmap2,
            2
        )
        cache?.addUrlEntry(
            "url/3",
            mockBitmap3,
            3
        )

        assertEquals(1000, cache?.getBitmap("url/1")?.byteCount)
    }

    @Test
    fun testReturnNullForBitmapNotInserted() {
        assertNull(cache?.getBitmap("url/5")?.byteCount)
    }

    @Test
    fun testReturnsSecondOccurrenceOfSameUrl() {

        mockBitmap5 = mock(Bitmap::class.java)
        whenever(mockBitmap5.byteCount).thenReturn(500)

        mockBitmap6 = mock(Bitmap::class.java)
        whenever(mockBitmap6.byteCount).thenReturn(600)

        cache?.addUrlEntry(
            "url/6",
            mockBitmap5,
            6
        )
        cache?.addUrlEntry(
            "url/6",
            mockBitmap6,
            6
        )
        assertEquals(600, cache!!.getCacheStats().currentCacheSize)
        assertEquals(0, cache!!.getCacheStats().evictionCount)

    }

    @Test
    fun testForEmptyCache() {
        cache!!.addUrlEntry("url/1", mockBitmap1, 1)
        cache!!.addUrlEntry("url/2", mockBitmap2, 2)
        cache!!.addUrlEntry("url/3", mockBitmap3, 3)
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
        cache!!.addUrlEntry("url/1", bitmap = mockBitmap1, 1)
        val stats = cache!!.getCacheStats()
        assertEquals(1000, stats.currentCacheSize)
    }

    @Test
    fun `currentSize decreases by bitmap byteCount after eviction`() {
        cache!!.addUrlEntry("url/1", bitmap = mockBitmap1, 1)
        cache!!.addUrlEntry("url/2", bitmap = mockBitmap2, 2)
        cache!!.addUrlEntry("url/3", bitmap = mockBitmap3, 3)
        //cache size = 3000
        mockBitmap4 = mock(Bitmap::class.java)
        whenever(mockBitmap4.byteCount).thenReturn(100)
        cache!!.addUrlEntry("url/4", mockBitmap4, 4)
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
        cache!!.addUrlEntry("url/1", mockBitmap4, 1)
        assertNull(cache!!.getBitmap("url/1"))
    }

    @Test
    fun testShowsBitmapExactlyEqualToMaxSizeIsInsertedSuccessfully() {
        val mockBitmap4 = mock(Bitmap::class.java)
        whenever(mockBitmap4.byteCount).thenReturn(3000)
        cache!!.addUrlEntry("url/4", mockBitmap4, 4)
        assertEquals(3000, cache!!.getBitmap("url/4")!!.byteCount)
    }

    @Test
    fun testShowsCurrentSizeNeverExceedsMaxSizeAfterMultipleAdditions() {
// fill cache completely
        cache!!.addUrlEntry("url/1", mockBitmap1, 1)  // 1000
        cache!!.addUrlEntry("url/2", mockBitmap2, 2)  // 500
        cache!!.addUrlEntry("url/3", mockBitmap3, 3)  // 1500
        // currentSize = 3000 = maxSize

        // keep inserting — evictions should keep currentSize within bounds
        val extraBitmap1 = mock(Bitmap::class.java)
        val extraBitmap2 = mock(Bitmap::class.java)
        val extraBitmap3 = mock(Bitmap::class.java)
        whenever(extraBitmap1.byteCount).thenReturn(800)
        whenever(extraBitmap2.byteCount).thenReturn(1200)
        whenever(extraBitmap3.byteCount).thenReturn(600)

        cache!!.addUrlEntry("url/4", extraBitmap1, 4)
        cache!!.addUrlEntry("url/5", extraBitmap2, 5)
        cache!!.addUrlEntry("url/6", extraBitmap3, 6)

        val stats = cache!!.getCacheStats()
        assertTrue(stats.currentCacheSize <= stats.maxSize)
    }

    @Test
    fun testPerformsRecentlyAccessedEntrySurvivesEvictionOverUnaccessedEntry() {
        cache!!.addUrlEntry("url/1", mockBitmap1, 1)  // 1000 - inserted first
        cache!!.addUrlEntry("url/2", mockBitmap2, 2)  // 500
        cache!!.addUrlEntry("url/3", mockBitmap3, 3)  // 1500
        // cache full at 3000

        cache!!.getBitmap("url/1")  // access url/1 — promotes it to MRU

        // insert new entry — forces eviction
        // url/2 should be evicted (LRU), not url/1 (recently accessed)
        val newBitmap = mock(Bitmap::class.java)
        whenever(newBitmap.byteCount).thenReturn(500)
        cache!!.addUrlEntry("url/4", newBitmap, 4)

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

        cache!!.addUrlEntry("url/small", smallBitmap, 1)
        cache!!.getBitmap("url/small")   // hit
        cache!!.getBitmap("url/small")   // hit
        cache!!.getBitmap("url/missing") // miss

        val stats = cache!!.getCacheStats()
        val temp = BigDecimal(stats.hitRate.toString()).setScale(2, RoundingMode.HALF_UP).toFloat()

        assertEquals(0.67f, temp)
    }

    //

    @Test
    fun testEvictionCountIncrementsOnEviction() {
        cache!!.addUrlEntry("url/1", mockBitmap1, 1)  // 1000
        cache!!.addUrlEntry("url/2", mockBitmap2, 2)  // 500
        cache!!.addUrlEntry("url/3", mockBitmap3, 3)  // 1500 — cache full

        val newBitmap = mock(Bitmap::class.java)
        whenever(newBitmap.byteCount).thenReturn(500)
        cache!!.addUrlEntry("url/4", newBitmap, 4)  // triggers eviction

        assertEquals(1, cache!!.getCacheStats().evictionCount)
    }

    @Test
    fun testHitAndMissCountsIncrementCorrectly() {
        cache!!.addUrlEntry("url/1", mockBitmap1, 1)
        cache!!.getBitmap("url/1")    // hit
        cache!!.getBitmap("url/1")    // hit
        cache!!.getBitmap("url/99")   // miss

        val stats = cache!!.getCacheStats()
        assertEquals(2, stats.hitCount)
        assertEquals(1, stats.missCount)
    }

    @Test
    fun testCacheUsableAfterClear() {
        cache!!.addUrlEntry("url/1", mockBitmap1, 1)
        cache!!.clear()

        cache!!.addUrlEntry("url/2", mockBitmap2, 2)
        assertNotNull(cache!!.getBitmap("url/2"))
        assertEquals(500, cache!!.getCacheStats().currentCacheSize)
    }

    @Test
    fun testEvictionCountStaysZeroWhenCacheNotFull() {
        cache!!.addUrlEntry("url/1", mockBitmap1, 1)  // 1000
        cache!!.addUrlEntry("url/2", mockBitmap2, 2)  // 500
        // currentSize = 1500, maxSize = 3000 — no eviction

        assertEquals(0, cache!!.getCacheStats().evictionCount)
    }

    @Test
    fun testReplacingTheOldestEntry() {
        val extraBitmap1 = mock(Bitmap::class.java)
        whenever(extraBitmap1.byteCount).thenReturn(1000)
        cache!!.addUrlEntry("url/1", extraBitmap1, 1)

        val extraBitmap2 = mock(Bitmap::class.java)
        whenever(extraBitmap2.byteCount).thenReturn(500)
        cache!!.addUrlEntry("url/2", extraBitmap2, 2)

        val extraBitmap3 = mock(Bitmap::class.java)
        whenever(extraBitmap2.byteCount).thenReturn(1500)
        cache!!.addUrlEntry("url/3", extraBitmap3, 3)

        val replacement = mock(Bitmap::class.java)
        whenever(replacement.byteCount).thenReturn(1200)
        cache!!.addUrlEntry("url/1", extraBitmap1, 3)

        assertEquals(2700, cache!!.getCacheStats().currentCacheSize)
        assertEquals(1, cache!!.getCacheStats().evictionCount)
    }

    @Test
    fun testReplacingNonOldestEntry() {
        val extraBitmap1 = mock(Bitmap::class.java)
        whenever(extraBitmap1.byteCount).thenReturn(1000)
        cache!!.addUrlEntry("url/1", extraBitmap1, 1)

        val extraBitmap2 = mock(Bitmap::class.java)
        whenever(extraBitmap2.byteCount).thenReturn(500)
        cache!!.addUrlEntry("url/2", extraBitmap2, 2)

        val extraBitmap3 = mock(Bitmap::class.java)
        whenever(extraBitmap3.byteCount).thenReturn(1500)
        cache!!.addUrlEntry("url/3", extraBitmap3, 3)

        whenever(extraBitmap3.byteCount).thenReturn(500)
        cache!!.addUrlEntry("url/3", extraBitmap3, 4)

        assertEquals(2000, cache!!.getCacheStats().currentCacheSize)
        assertEquals(0, cache!!.getCacheStats().evictionCount)
    }

    @Test
    fun testEvictionRecordCarriesTheEvictedImagesOwnNumber() {
        val extraBitmap1 = mock(Bitmap::class.java)
        whenever(extraBitmap1.byteCount).thenReturn(3000)
        cache!!.addUrlEntry("url/1", extraBitmap1, 11)

        val extraBitmap2 = mock(Bitmap::class.java)
        whenever(extraBitmap2.byteCount).thenReturn(3000)
        cache!!.addUrlEntry("url/2", extraBitmap1, 99)

        assertEquals(11, cache!!.getCacheStats().recentEvictions.first().sequenceNumber)

    }

    @Test
    fun testHistoryCap() {
        cache = LRUCache(1000)

        val extraBitmap1 = mock(Bitmap::class.java)
        val extraBitmap2 = mock(Bitmap::class.java)
        val extraBitmap3 = mock(Bitmap::class.java)
        val extraBitmap4 = mock(Bitmap::class.java)
        val extraBitmap5 = mock(Bitmap::class.java)
        val extraBitmap6 = mock(Bitmap::class.java)
        val extraBitmap7 = mock(Bitmap::class.java)
        val extraBitmap8 = mock(Bitmap::class.java)
        val extraBitmap9 = mock(Bitmap::class.java)
        val extraBitmap10 = mock(Bitmap::class.java)
        val extraBitmap11 = mock(Bitmap::class.java)
        val extraBitmap12 = mock(Bitmap::class.java)
        val extraBitmap13 = mock(Bitmap::class.java)
        val extraBitmap14 = mock(Bitmap::class.java)
        val extraBitmap15 = mock(Bitmap::class.java)

        whenever(extraBitmap1.byteCount).thenReturn(500)
        whenever(extraBitmap2.byteCount).thenReturn(500)
        whenever(extraBitmap3.byteCount).thenReturn(500)
        whenever(extraBitmap4.byteCount).thenReturn(500)
        whenever(extraBitmap5.byteCount).thenReturn(500)
        whenever(extraBitmap6.byteCount).thenReturn(500)
        whenever(extraBitmap7.byteCount).thenReturn(500)
        whenever(extraBitmap8.byteCount).thenReturn(500)
        whenever(extraBitmap9.byteCount).thenReturn(500)
        whenever(extraBitmap10.byteCount).thenReturn(500)
        whenever(extraBitmap11.byteCount).thenReturn(500)
        whenever(extraBitmap12.byteCount).thenReturn(500)
        whenever(extraBitmap13.byteCount).thenReturn(500)
        whenever(extraBitmap14.byteCount).thenReturn(500)
        whenever(extraBitmap15.byteCount).thenReturn(500)

        cache!!.addUrlEntry("url/1", extraBitmap1, 1)
        cache!!.addUrlEntry("url/2", extraBitmap2, 2)
        cache!!.addUrlEntry("url/3", extraBitmap3, 3)
        cache!!.addUrlEntry("url/4", extraBitmap4, 4)
        cache!!.addUrlEntry("url/5", extraBitmap5, 5)
        cache!!.addUrlEntry("url/6", extraBitmap6, 6)
        cache!!.addUrlEntry("url/7", extraBitmap7, 7)
        cache!!.addUrlEntry("url/8", extraBitmap8, 8)
        cache!!.addUrlEntry("url/9", extraBitmap9, 9)
        cache!!.addUrlEntry("url/10", extraBitmap10, 10)
        cache!!.addUrlEntry("url/11", extraBitmap11, 11)
        cache!!.addUrlEntry("url/12", extraBitmap12, 12)
        cache!!.addUrlEntry("url/13", extraBitmap13, 13)
        cache!!.addUrlEntry("url/14", extraBitmap14, 14)
        cache!!.addUrlEntry("url/15", extraBitmap15, 15)

        assertEquals(13, cache!!.getCacheStats().evictionCount)
        assertEquals(10, cache!!.getCacheStats().recentEvictions.size)
    }
        @Test
        fun testClear() {
            cache!!.clear()
            cache!!.clear()

            assertEquals(2, cache!!.cacheCleared.value)
            assertEquals(0, cache!!.getCacheStats().evictionCount)
            assertEquals(0, cache!!.getCacheStats().recentEvictions.size)

        }


}
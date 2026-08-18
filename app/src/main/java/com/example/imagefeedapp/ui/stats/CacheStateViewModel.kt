package com.example.imagefeedapp.ui.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagefeedapp.data.cache.LRUCache
import com.example.imagefeedapp.domain.model.CacheStats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CacheStateViewModel @Inject constructor(
    private val lruImageCache: LRUCache
): ViewModel() {

    private val _cacheStats = MutableStateFlow(lruImageCache.getCacheStats())
    val cacheStats: StateFlow<CacheStats> = _cacheStats.asStateFlow()

    init {
        viewModelScope.launch {
            while (true) {
                _cacheStats.value = lruImageCache.getCacheStats()
                delay(1000L)
            }
        }
    }

    fun clearCache() {
        lruImageCache.clear()
        _cacheStats.value = lruImageCache.getCacheStats()
    }
}
package com.example.imagefeedapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun Context.observeNetworkState(): Flow<Boolean> = callbackFlow {
    val connectivityManager = getSystemService(ConnectivityManager::class.java)
    val activeNetwork= connectivityManager.activeNetwork
    val networkCapabilities= connectivityManager.getNetworkCapabilities(activeNetwork)
    val initialState=
        networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)==true
    trySend(initialState)

    val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) { trySend(true) }
        override fun onLost(network: Network) { trySend(false) }
    }
    connectivityManager.registerDefaultNetworkCallback(callback)
    awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
}
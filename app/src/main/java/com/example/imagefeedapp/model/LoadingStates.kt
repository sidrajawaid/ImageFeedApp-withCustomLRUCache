package com.example.imagefeedapp.model

sealed class LoadingStates {
    object onLoading : LoadingStates();
    data class NetworkState(val isConnected: Boolean) : LoadingStates();
    data class onSuccess(val message: String) : LoadingStates();
    data class onFailure(val errorMessage: String) : LoadingStates();
    data class onCancelled(val cancelMessage: String) : LoadingStates();
}
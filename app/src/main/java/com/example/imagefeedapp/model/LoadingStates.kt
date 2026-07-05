package com.example.imagefeedapp.model

sealed class LoadingStates
{
  object LoadingStates;
    data class NetworkState(val isConnected: Boolean)
    data class onSuccess(val message:String);
    data class onFailure(val errorMessage:String);
  data class onCancelled(val cancelMessage:String);
}
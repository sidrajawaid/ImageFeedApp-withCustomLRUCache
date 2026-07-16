package com.example.imagefeedapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.imagefeedapp.ui.items.FooterFetchingItem
import com.example.imagefeedapp.viewmodel.DashboardViewModel
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.imagefeedapp.model.FeedUiState
import com.valentinilk.shimmer.shimmer


@Composable
fun DashboardScreen(innerPadding: PaddingValues, dashboardViewModel: DashboardViewModel = hiltViewModel()) {

    val isconnected= dashboardViewModel.isConnected.collectAsStateWithLifecycle()

    Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
          if(!isconnected.value)
          NoConnectionScreen()
        else
           when(dashboardViewModel.uiState.collectAsState().value){

               is FeedUiState.Cancelled -> TODO()
               is FeedUiState.Failure -> EmptyFeedScreen()
               is FeedUiState.Loading ->  {Modifier.shimmer()
                   FooterFetchingItem()}
               is FeedUiState.Success -> TODO()
           }

    }
}

@Preview
@Composable
fun Preview(){
    val fakePadding = PaddingValues(
        top = 56.dp,    // typical TopAppBar height
        bottom = 16.dp, // e.g., bottom nav or FAB spacing
        start = 8.dp,
        end = 8.dp
    )
    DashboardScreen(fakePadding)
}
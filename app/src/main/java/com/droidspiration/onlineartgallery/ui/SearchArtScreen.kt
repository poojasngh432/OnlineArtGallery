package com.droidspiration.onlineartgallery.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun SearchArtScreen(viewModel: ArtRecordsViewModel = hiltViewModel(), navController: NavController) {
    var query by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "What do you want to see?")
        TextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { viewModel.searchArt(query) }) {
            Text("Search")
        }

        uiState.let {
//            navController.navigate("details")
        }

        when (uiState) {
            is UIState.Loading -> {
                CircularProgressIndicator() // Show a loading indicator
            }
            is UIState.Success -> {
//                val artPiece = (uiState as UIState.Success)
//                ArtPieceDetail(artPiece)  // Show art piece details
//                navController.navigate("getArt")
            }
            is UIState.Error -> {
                val message = (uiState as UIState.Error).message
                ErrorScreen(message)  // Show error message
            }
            is UIState.Idle -> {
                // Initial state, you can display an empty screen or a search prompt
            }
        }
    }
}

//@Composable
//fun ArtPieceDetail(artPiece: ArtPieceData) {
//    // Display art details
//    Column {
//        Text(text = artPiece.name)
//        // Add more details and images
//    }
//}
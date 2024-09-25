package com.droidspiration.onlineartgallery.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidspiration.onlineartgallery.data.network.ArtPieceData

@Composable
fun ShowRandomArtScreen(viewModel: GetRandomArtVewModel = hiltViewModel(), objectId: String?) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is UIState.Loading -> {
            CircularProgressIndicator() // Show a loading indicator
        }
        is UIState.Success -> {
            val artPiece = (uiState as UIState.Success).data
            ArtPieceDetail(artPiece)  // Show art piece details
        }
        is UIState.Error -> {
            val message = (uiState as UIState.Error).message
            ErrorScreen(message)  // Show error message
        }
        is UIState.Idle -> {
            // Initial state, you can display an empty screen or a search prompt
        }
    }

//    uiState.let { artObject ->
//        Column(modifier = Modifier.padding(16.dp)) {
//            Image(
//                painter = rememberAsyncImagePainter(artObject.primaryImage),
//                contentDescription = artObject.name,
//                modifier = Modifier.size(300.dp)
//            )
//            Text(text = artObject.name)
//            Text(text = artObject.artistDisplayName)
//            Button(onClick = { viewModel.getRandomArt() }) {
//                Text(text = "Get random art")
//            }
//        }
//    }
}

@Composable
fun ArtPieceDetail(artPiece: ArtPieceData) {
    // Display art details
    Column {
        Text(text = artPiece.name)
        // Add more details and images
    }
}

@Composable
fun ErrorScreen(message: String) {
    Text(text = "Error: $message", color = Color.Red)
}
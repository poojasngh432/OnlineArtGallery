package com.droidspiration.onlineartgallery.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.droidspiration.onlineartgallery.data.local.Museum

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    val museums = listOf(
        Museum(
            "Metropolitan Museum of Art",
            "https://cdn.sanity.io/images/cctd4ker/production/7c82dc04b9b7cd6fdc7050baac63f9a0fa1d0b58-3200x1800.jpg?w=2048&q=75&fit=clip&auto=format"
        ),
        Museum(
            "Art Institute of Chicago",
            "https://www.artic.edu/iiif/2/25c31d8d-21a4-9ea1-1d73-6a2eca4dda7e/full/1686,/0/default.jpg"
        ),
        Museum(
            "Harvard Art Museums",
            "https://harvardartmuseums.org/assets/images/pages/on-view-now-1350-880.jpg"
        )
    )

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {
        items(museums) { museum ->
            MuseumCard(museum = museum, navController)
        }
    }
}

@Composable
fun MuseumCard(museum: Museum, navController: NavController) {
    Card(
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 14.dp, end = 14.dp)
            .wrapContentHeight()
            .clickable {
                navController.navigate("search")
            }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(data = museum.imageUrl),
                contentDescription = museum.name,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = museum.name,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}
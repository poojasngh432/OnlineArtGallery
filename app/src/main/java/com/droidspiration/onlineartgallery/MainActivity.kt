package com.droidspiration.onlineartgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.droidspiration.onlineartgallery.di.ArtGalleryApp
import com.droidspiration.onlineartgallery.ui.theme.OnlineArtGalleryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnlineArtGalleryTheme {
                ArtGalleryApp()
            }
        }
    }
}
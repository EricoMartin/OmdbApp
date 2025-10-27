package com.basebox.omdbapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.basebox.omdbapp.ui.viewmodel.OmdbViewModel
import com.basebox.omdbapp.util.Resource

@Composable
fun DetailScreen(
    imdbId: String,
    viewModel: OmdbViewModel,
    onBackClicked: () -> Unit
) {
    val movie by viewModel.movieDetail.collectAsState()
    val loading by viewModel.isLoading.collectAsState()

    BackHandler(enabled = true) {
        onBackClicked()
    }
    LaunchedEffect(imdbId) {
        viewModel.getDetail(imdbId, {})
    }

    when (val state = loading) {
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is Resource.Success -> {
            movie?.let {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(36.dp)
                            .clickable(onClick = onBackClicked )
                    )
                    Image(
                        painter = rememberAsyncImagePainter(it.poster),
                        contentDescription = it.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .height(400.dp),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = it.title ?: "N/A",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = "Released: ${it.year ?: "N/A"} (${it.year ?: "N/A"})",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )

                    Text(
                        text = "Genre: ${it.type ?: "N/A"}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )


                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text =  "No plot available.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "IMDb Rating: N/A",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
            } ?: Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No data found.", color = Color.Black)
            }
        }

        is Resource.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error: ${state.message ?: "Unknown error occurred while loading data."}", color = Color.Red)
            }
        }
    }
}


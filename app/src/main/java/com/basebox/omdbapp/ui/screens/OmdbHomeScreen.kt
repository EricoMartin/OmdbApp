package com.basebox.omdbapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.basebox.omdbapp.ui.components.MovieGrid
import com.basebox.omdbapp.ui.components.SearchBar
import com.basebox.omdbapp.ui.viewmodel.OmdbViewModel
import com.basebox.omdbapp.util.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OmdbHomeScreen(
    viewModel: OmdbViewModel = hiltViewModel(),
    onMovieClicked: (String) -> Unit
) {
    val movies by viewModel.cachedMovies.collectAsState()
    val searchState by viewModel.searchState.collectAsState()
    val selectedMovieId by viewModel.selectedMovieId.collectAsState()
    val query by  viewModel.searchQuery.collectAsState()


    LaunchedEffect(selectedMovieId) {
    }
    Scaffold(
        containerColor = Color(0xFFF0F0F7),
        topBar = {
            if (selectedMovieId == null) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Movie app",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            }
        },
        floatingActionButton = {
            if (selectedMovieId == null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding( bottom = 80.dp),
                    contentAlignment = Alignment.BottomStart // 👈 bottom-left position
                ) {
                    FloatingActionButton(
                        onClick = {},
                        shape = RoundedCornerShape(48.dp),
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                }
            }
        },
                floatingActionButtonPosition = FabPosition.Start,
    ) { innerPadding ->
        when {
            selectedMovieId != null -> {
                DetailScreen(
                    selectedMovieId!!,
                    viewModel,
                    {viewModel.clearSelectedMovie()}
                )
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp)
                ) {
                    SearchBar(
                        query = query,
                        onQueryChange = viewModel::onQueryChanged,
                    )
                    Spacer(modifier = Modifier.height(48.dp))
                    when (val state = searchState) {
                        is Resource.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }

                        is Resource.Error -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = state.message!!, color = Color.Red)
                            }
                        }

                        else -> {
                            MovieGrid(
                                movies = movies,
                                onMovieClicked = { imdbId ->
                                    viewModel.selectMovie(imdbId)
                                }
                            )
                        }
                    }
                }
            }
        }
        }
}
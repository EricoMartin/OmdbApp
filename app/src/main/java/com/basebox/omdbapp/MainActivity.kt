package com.basebox.omdbapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.basebox.omdbapp.ui.screens.DetailScreen
import com.basebox.omdbapp.ui.screens.OmdbHomeScreen
import com.basebox.omdbapp.ui.theme.OMDbAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OMDbAppTheme {
                var selectedMovieId by rememberSaveable { mutableStateOf<String?>(null) }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    if (selectedMovieId == null) {
                        OmdbHomeScreen(viewModel = hiltViewModel(), onMovieClicked = {
                                imdbId ->
                            selectedMovieId = imdbId
                        })
                    } else {
                        DetailScreen(
                            imdbId = selectedMovieId!!,
                            viewModel = hiltViewModel(),
                            onBackClicked = {
                                selectedMovieId = null
                            })
                    }
                    BackHandler(enabled = true) {
                        selectedMovieId = null
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OMDbAppTheme {
        Greeting("Android")
    }
}
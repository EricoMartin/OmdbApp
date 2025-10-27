package com.basebox.omdbapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.basebox.omdbapp.data.local.entity.MovieEntity

@Composable
fun MovieGrid(
    movies: List<MovieEntity>,
    onMovieClicked: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(movies, key = { it.imdbID }) { movie ->
            MovieItem(
                movie = movie,
                modifier = Modifier.clickable { onMovieClicked(movie.imdbID) }
            )
        }
    }
}
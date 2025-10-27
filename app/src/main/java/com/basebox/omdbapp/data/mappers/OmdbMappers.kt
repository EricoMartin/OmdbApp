package com.basebox.omdbapp.data.mappers

import com.basebox.omdbapp.data.local.entity.MovieEntity
import com.basebox.omdbapp.data.remote.SearchItem

fun SearchItem.toEntity(): MovieEntity {
    return MovieEntity(
        imdbID = this.imdbID ?: "",
        title = this.title,
        year = this.year,
        type = this.type,
        poster = this.poster
    )
}
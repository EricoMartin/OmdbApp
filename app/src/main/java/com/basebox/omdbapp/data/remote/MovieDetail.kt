package com.basebox.omdbapp.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetail(
    @Json(name = "Title") val title: String?,
    @Json(name = "Year") val year: String?,
    @Json(name = "Rated") val rated: String?,
    @Json(name = "Released") val released: String?,
    @Json(name = "Runtime") val runtime: String?,
    @Json(name = "Genre") val genre: String?,
    @Json(name = "Director") val director: String?,
    @Json(name = "Writer") val writer: String?,
    @Json(name = "Actors") val actors: String?,
    @Json(name = "Plot") val plot: String?,
    @Json(name = "Language") val language: String?,
    @Json(name = "Country") val country: String?,
    @Json(name = "Poster") val poster: String?,
    @Json(name = "imdbID") val imdbID: String?,
    @Json(name = "Response") val response: String?,
    @Json(name = "Error") val error: String?
)


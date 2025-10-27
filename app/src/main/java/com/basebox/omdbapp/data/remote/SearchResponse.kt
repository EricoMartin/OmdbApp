package com.basebox.omdbapp.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    @Json(name = "Search") val search: List<SearchItem>?,
    @Json(name = "totalResults") val totalResults: String?,
    @Json(name = "Response") val response: String?,
    @Json(name = "Error") val error: String?
)
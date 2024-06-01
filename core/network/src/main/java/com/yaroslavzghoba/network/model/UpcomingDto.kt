package com.yaroslavzghoba.network.model

import kotlinx.serialization.SerialName

data class UpcomingDto(
    @SerialName("dates") val dates: DatesDto,
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<MovieDto>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int,
)
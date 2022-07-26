package com.popular.broadcast.data.dto.model

data class Result(
    val `abstract`: String,
    val adx_keywords: String,
    val asset_id: Long,
    val byline: String,
    val column: String,
    val eta_id: Int,
    val id: Long,
    val nytdsection: String,
    val published_date: String?,
    val section: String,
    val source: String,
    val subsection: String,
    val title: String,
    val type: String,
    val updated: String?,
    val uri: String,
    val url: String,
    val media: List<Media>,
    val des_facet: List<String>,
    val geo_facet: List<String>,
    val org_facet: List<String>,
    val per_facet: List<String>
)
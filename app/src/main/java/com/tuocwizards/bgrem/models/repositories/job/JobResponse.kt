package com.tuocwizards.bgrem.models.repositories.job

data class JobResponse(
    val source_url: String,
    val media_type: String,
    val size: Int,
    val id: Int
)

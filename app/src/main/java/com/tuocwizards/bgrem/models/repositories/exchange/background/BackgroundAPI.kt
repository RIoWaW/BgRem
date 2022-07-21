package com.tuocwizards.bgrem.models.repositories.exchange.background

import retrofit2.http.GET
import retrofit2.http.Headers

interface BackgroundAPI {
    @GET("./bg")
    @Headers("Content_Type: application/json")
    suspend fun getBackgrounds() : MutableList<BackgroundItem>
}
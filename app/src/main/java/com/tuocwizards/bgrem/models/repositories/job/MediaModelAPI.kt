package com.tuocwizards.bgrem.models.repositories.job

import okhttp3.MultipartBody
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MediaModelAPI {

    @POST("./jobs")
    @Headers("Content_Type: multipart/form-data", "Accept: application/json")
    @Multipart
    suspend fun createJob(@Part body: MultipartBody.Part): JobResponse
}
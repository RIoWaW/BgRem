package com.tuocwizards.bgrem.viewmodels

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tuocwizards.bgrem.mainComponent
import com.tuocwizards.bgrem.models.datastorages.storages.BackgroundStorage
import com.tuocwizards.bgrem.models.repositories.job.MediaModelAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

class MainPageVM(application: Application): AndroidViewModel(application) {

    private lateinit var mediaModelAPI: MediaModelAPI

    init {
        application.mainComponent.mediaModelAPI
    }

    fun sendPhoto(mediaUri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val body = createBody(mediaUri)
                mediaModelAPI.createJob(body)
            } catch (e: Exception) {
                Log.e("E", e.toString())
            }
        }
    }

    private fun createBody(mediaUri: Uri): MultipartBody.Part {

        val tmp = getApplication<Application?>().contentResolver.openInputStream(mediaUri)
        var bitmap = BitmapFactory.decodeStream(tmp)
        bitmap = Bitmap.createBitmap(bitmap)

        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, stream)
        val byteArray = stream.toByteArray()

        return MultipartBody.Part.createFormData(
            "photo[content]", "photo",
            byteArray.toRequestBody("*".toMediaTypeOrNull(), 0, byteArray.size)
        )
    }
}
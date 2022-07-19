package com.tuocwizards.bgrem.viewmodels

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuocwizards.bgrem.di.MainComponent
import com.tuocwizards.bgrem.mainComponent
import com.tuocwizards.bgrem.models.repositories.exchangebackground.BackgroundAPI
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainPageVM: ViewModel() {

    @Inject
    lateinit var backgroundAPI: BackgroundAPI

    init {

    }

    fun sendPhoto(mediaUri: Uri) {    }


    fun sendVideo(mediaUri: Uri) {    }

    fun getB(mainComponent: MainComponent) {
        mainComponent.inject(this)
        viewModelScope.launch {
            try {
                val tmp = backgroundAPI.getBackgrounds()
                val u=1

            } catch (e: Exception) {
                Log.e("TTT", e.toString())
            }
        }
    }
}
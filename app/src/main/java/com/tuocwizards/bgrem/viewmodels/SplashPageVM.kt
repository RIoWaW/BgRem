package com.tuocwizards.bgrem.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tuocwizards.bgrem.mainComponent
import com.tuocwizards.bgrem.models.datastorages.storages.BackgroundStorage
import com.tuocwizards.bgrem.models.repositories.exchange.background.BackgroundAPI
import kotlinx.coroutines.launch

class SplashPageVM(application: Application): AndroidViewModel(application) {

    private var backgroundAPI: BackgroundAPI

    init {
        backgroundAPI = application.mainComponent.backgroundAPI
    }

    fun getBackgrounds() {
        viewModelScope.launch {
            BackgroundStorage.backgrounds = backgroundAPI.getBackgrounds()
        }
    }
}
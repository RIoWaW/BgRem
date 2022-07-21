package com.tuocwizards.bgrem.viewmodels

import androidx.lifecycle.ViewModel
import com.tuocwizards.bgrem.models.datastorages.storages.BackgroundStorage
import com.tuocwizards.bgrem.models.repositories.exchange.background.BackgroundItem

class BackgroundTabItemVM: ViewModel() {

    fun getColorBackground(): List<BackgroundItem> {
        val tmp = BackgroundStorage.backgrounds.filter { it.group == "color" }
        return tmp
    }

    fun getPictureBackground(): List<BackgroundItem> {
        val tmp = BackgroundStorage.backgrounds.filter {
            it.group == "image" || it.group == "video"
        }
        return tmp
    }

    fun getUserBackground(): List<BackgroundItem> {
        return BackgroundStorage.backgrounds.filter { it.group == "user" }
    }


}
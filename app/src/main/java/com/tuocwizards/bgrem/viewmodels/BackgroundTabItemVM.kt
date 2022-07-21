package com.tuocwizards.bgrem.viewmodels

import androidx.lifecycle.ViewModel
import com.tuocwizards.bgrem.models.datastorages.storages.BackgroundStorage
import com.tuocwizards.bgrem.models.repositories.exchange.background.BackgroundItem

class BackgroundTabItemVM: ViewModel() {

    fun getColorBackground(): List<BackgroundItem> {
        return BackgroundStorage.backgrounds.filter { it.group == "color" }
    }

    //add it.group == "video" in filter just to increase amount of elements
    //after adding logic for video this fun will be separated
    fun getPictureBackground(): List<BackgroundItem> {
        return BackgroundStorage.backgrounds.filter {
            it.group == "image" || it.group == "video"
        }
    }

    fun getUserBackground(): List<BackgroundItem> {
        return BackgroundStorage.backgrounds.filter { it.group == "user" }
    }

    fun addUserBackground(path: String) {
        BackgroundStorage.addUserImageBackground(path)
    }

}
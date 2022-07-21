package com.tuocwizards.bgrem.models.datastorages.storages

import com.tuocwizards.bgrem.models.repositories.exchange.background.BackgroundItem

object BackgroundStorage {

    var backgrounds: MutableList<BackgroundItem> = mutableListOf()

    fun addUserImageBackground(path: String) {
        backgrounds.add(
            BackgroundItem(
            id = null,
            group = "user",
            color = null,
            file_url = path,
            thumbnail_url = null,
            poster_url = null)
        )
    }
}

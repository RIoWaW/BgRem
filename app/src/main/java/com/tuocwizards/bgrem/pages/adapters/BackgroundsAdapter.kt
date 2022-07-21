package com.tuocwizards.bgrem.pages.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tuocwizards.bgrem.R
import com.tuocwizards.bgrem.databinding.BackgroundItemBinding
import com.tuocwizards.bgrem.models.repositories.exchange.background.BackgroundItem

class BackgroundsAdapter: RecyclerView.Adapter<BackgroundsAdapter.BackgroundsViewHolder>() {

    var backgrounds: List<BackgroundItem> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackgroundsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BackgroundItemBinding.inflate(inflater, parent, false)
        return BackgroundsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BackgroundsViewHolder, position: Int) {
        val background = backgrounds[position]
        with(holder.binding) {
            when (background.group) {
                "color" -> backgroundPreview.setImageDrawable(
                    ColorDrawable(Color.parseColor(background.color))
                )
                "image" -> backgroundPreview.load(background.thumbnail_url)
                "user" -> backgroundPreview.load(background.file_url)
                "video" -> backgroundPreview.load(background.poster_url)
                else -> backgroundPreview.load(R.drawable.no_image_icon)
            }
        }
    }

    override fun getItemCount(): Int = backgrounds.size

    class BackgroundsViewHolder(val binding: BackgroundItemBinding)
        : RecyclerView.ViewHolder(binding.root)
}
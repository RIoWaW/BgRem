package com.tuocwizards.bgrem.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.android.material.tabs.TabLayoutMediator
import com.tuocwizards.bgrem.R
import com.tuocwizards.bgrem.databinding.SelectBackgroundPageBinding
import com.tuocwizards.bgrem.pages.adapters.ViewPagerAdapter

class SelectBackgroundPage : BaseFragment<SelectBackgroundPageBinding>(
    SelectBackgroundPageBinding::inflate
) {

    private val PATH = "pathKey"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SelectBackgroundPageBinding.inflate(inflater)
        binding.backButton.setOnClickListener{
            findNavController().navigateUp()
        }
        val path = arguments?.getString(PATH)
        binding.photo.load(path)
        binding.viewpager.adapter = ViewPagerAdapter(activity!!)
        TabLayoutMediator(binding.tabLayout, binding.viewpager){
            tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            0 -> getString(R.string.without_background)
            1 -> getString(R.string.user_background)
            2 -> getString(R.string.color_background)
            3 -> getString(R.string.picture_background)
            else -> null
        }
    }
}
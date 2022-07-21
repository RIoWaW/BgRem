package com.tuocwizards.bgrem.pages

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuocwizards.bgrem.databinding.BackgroundTabItemBinding
import com.tuocwizards.bgrem.pages.adapters.BackgroundsAdapter
import com.tuocwizards.bgrem.viewmodels.BackgroundTabItemVM

class BackgroundTabItem : BaseFragment<BackgroundTabItemBinding>(
    BackgroundTabItemBinding::inflate
) {

    private val viewModel: BackgroundTabItemVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BackgroundTabItemBinding.inflate(inflater)
        binding.backgroundsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val adapter = BackgroundsAdapter()
        val position = arguments?.getInt(POSITION)
        selectContentByPosition(position, adapter)
        return binding.root
    }

    private fun selectContentByPosition(position: Int?, adapter: BackgroundsAdapter){
        when(position) {
            0 -> setWithoutBackgroundContent(adapter)
            1 -> setUserBackgroundContent(adapter)
            2 -> setColorBackgroundContent(adapter)
            3 -> setPictureBackgroundContent(adapter)
        }
    }

    private fun setWithoutBackgroundContent(adapter: BackgroundsAdapter) {
        adapter.backgrounds = emptyList()
        binding.backgroundsRecyclerView.adapter = adapter
    }

    private fun setUserBackgroundContent(adapter: BackgroundsAdapter) {
        adapter.backgrounds = viewModel.getUserBackground()
        binding.backgroundsRecyclerView.adapter = adapter
        binding.uploadBackgroundButton.visibility = View.VISIBLE
        binding.uploadBackgroundButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            uploadPhoto.launch(intent)
        }
    }

    private fun setColorBackgroundContent(adapter: BackgroundsAdapter) {
        adapter.backgrounds = viewModel.getColorBackground()
        binding.backgroundsRecyclerView.adapter = adapter
    }

    private fun setPictureBackgroundContent(adapter: BackgroundsAdapter) {
        adapter.backgrounds = viewModel.getPictureBackground()
        binding.backgroundsRecyclerView.adapter = adapter
    }

    private val uploadPhoto = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        try {
            viewModel.addUserBackground(it.data!!.dataString!!)
            val adapter = BackgroundsAdapter()
            adapter.backgrounds = viewModel.getUserBackground()
            binding.backgroundsRecyclerView.adapter = adapter
        }
        catch (e: Exception){
            Log.e("E", e.toString())
        }
    }

    companion object {
        const val POSITION = "position"
        @JvmStatic
        fun newInstance(position: Int) = BackgroundTabItem().apply {
            arguments = Bundle().apply { putInt(POSITION, position) }
        }
    }
}
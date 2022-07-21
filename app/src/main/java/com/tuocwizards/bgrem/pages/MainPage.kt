package com.tuocwizards.bgrem.pages

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tuocwizards.bgrem.R
import com.tuocwizards.bgrem.databinding.BottomDialogWindowBinding
import com.tuocwizards.bgrem.databinding.MainPageBinding
import com.tuocwizards.bgrem.models.enums.MediaType
import com.tuocwizards.bgrem.viewmodels.MainPageVM

class MainPage: BaseFragment<MainPageBinding>(
    MainPageBinding::inflate
) {

    private val viewModel: MainPageVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainPageBinding.inflate(inflater)
        binding.apply {
            infoButton.setOnClickListener { goToInfoPage() }
            photoButton.setOnClickListener { showBottomDialog(MediaType.PHOTO) }
            videoButton.setOnClickListener { showBottomDialog(MediaType.SHORT_VIDEO) }
        }
        return binding.root
    }

    private fun goToInfoPage() {
        findNavController().navigate(R.id.action_mainPage_to_infoPage)
    }

    private fun showBottomDialog(mediaType: MediaType) {
        val bottomSheetDialog = BottomSheetDialog(context!!)
        val bottomSheetView: View = layoutInflater
            .inflate(R.layout.bottom_dialog_window, requireActivity().findViewById(R.id.bottom_dialog_container))

        BottomDialogWindowBinding.bind(bottomSheetView).apply {
            when (mediaType) {
                MediaType.PHOTO -> setPhotoProperties(bottomSheetDialog, this)
                MediaType.SHORT_VIDEO -> setVideoProperties(bottomSheetDialog, this)
            }
            cancelButton.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }
    }

    private val sendPhoto = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        try {
            viewModel.sendPhoto(it.data!!.data!!)
            //navigate on loaderPage and wait result
            //but I can't get response, so just navigate to SelectBackgroundPage
            findNavController().navigate(R.id.action_mainPage_to_selectBackgroundPage)
        }
        catch (e: Exception){
            Log.e("E", e.toString())
            showErrorToast()
        }
    }

    private fun setPhotoProperties(bottomSheetDialog: BottomSheetDialog,
        bottomDialogBinding: BottomDialogWindowBinding) =
        bottomDialogBinding.apply {
        noteTitle.isVisible = false
        noteText.isVisible = false
        cameraButton.setOnClickListener {
            sendPhoto.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            bottomSheetDialog.dismiss()
        }
        galleryButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            sendPhoto.launch(intent)
            bottomSheetDialog.dismiss()
        }
    }


    private fun showErrorToast() {
        Toast.makeText(context, "Problem with loading media file", Toast.LENGTH_LONG)
            .show()
    }

    private fun setVideoProperties(bottomSheetDialog: BottomSheetDialog,
        bottomDialogBinding: BottomDialogWindowBinding) =
        bottomDialogBinding.apply {

        cameraButton.setOnClickListener {
            //getVideo.launch(Intent(MediaStore.ACTION_VIDEO_CAPTURE))
            bottomSheetDialog.dismiss()
        }
        galleryButton.setOnClickListener {
            //val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
            //getVideo.launch(intent)
            bottomSheetDialog.dismiss()
        }
    }
}
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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tuocwizards.bgrem.R
import com.tuocwizards.bgrem.databinding.BottomDialogWindowBinding
import com.tuocwizards.bgrem.databinding.MainPageBinding
import com.tuocwizards.bgrem.mainComponent
import com.tuocwizards.bgrem.types.MediaType
import com.tuocwizards.bgrem.viewmodels.MainPageVM

class MainPage: Fragment(R.layout.main_page) {

    private lateinit var viewModel: MainPageVM
    private lateinit var binding: MainPageBinding
    private val EXCEPTION_TEG = "EXCEPTION"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MainPageVM::class.java]
        binding = MainPageBinding.inflate(inflater, container, false)
        binding.apply {
            infoButton.setOnClickListener { goToInfoPage() }
            photoButton.setOnClickListener { showBottomDialog(MediaType.PHOTO) }
            videoButton.setOnClickListener { showBottomDialog(MediaType.SHORT_VIDEO) }
        }
        get()
        return binding.root
    }

    fun get() {
        try {
            viewModel.getB(context!!.mainComponent)
        } catch (e: Exception) {
            Log.e("TTT", e.toString())
        }

    }

    private fun goToInfoPage() {
        findNavController().navigate(R.id.action_mainPage_to_infoPage)
    }

    private fun showBottomDialog(mediaType: MediaType) {
        val bottomSheetDialog = BottomSheetDialog(context!!)
        val bottomSheetView: View = layoutInflater
            .inflate(R.layout.bottom_dialog_window, requireActivity().findViewById(R.id.bottom_dialog_container))

        BottomDialogWindowBinding.bind(bottomSheetView).apply {
            if (mediaType == MediaType.PHOTO) {
                setPhotoProperties(bottomSheetDialog, this)
                bottomSheetDialog.dismiss()
            } else {
                setVideoProperties(bottomSheetDialog, this)
                bottomSheetDialog.dismiss()
            }
            cancelButton.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }
    }

    private val getPhoto = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        binding.logo.load(it.data!!.dataString)
        try {
            viewModel.sendPhoto(it.data!!.data!!)
        }
        catch (e: Exception){
            Log.e(EXCEPTION_TEG, e.toString())
            showErrorToast()
        }
    }

    private val getVideo = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        binding.logo.load(it.data!!.dataString)
        try {
            viewModel.sendVideo(it.data!!.data!!)
        }
        catch (e: Exception){
            Log.e(EXCEPTION_TEG, e.toString())
            showErrorToast()
        }
    }

    private fun setPhotoProperties(bottomSheetDialog: BottomSheetDialog,
        bottomDialogBinding: BottomDialogWindowBinding) =
        bottomDialogBinding.apply {
        noteTitle.isVisible = false
        noteText.isVisible = false
        cameraButton.setOnClickListener {
            getPhoto.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            bottomSheetDialog.dismiss()
        }
        galleryButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            getPhoto.launch(intent)
            bottomSheetDialog.dismiss()
        }
    }

    private fun setVideoProperties(bottomSheetDialog: BottomSheetDialog,
        bottomDialogBinding: BottomDialogWindowBinding) =
        bottomDialogBinding.apply {

        cameraButton.setOnClickListener {
            getVideo.launch(Intent(MediaStore.ACTION_VIDEO_CAPTURE))
            bottomSheetDialog.dismiss()
        }
        galleryButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
            getVideo.launch(intent)
            bottomSheetDialog.dismiss()
        }
    }

    private fun showErrorToast() {
        Toast.makeText(context, "Problem with loading photo", Toast.LENGTH_LONG)
            .show()
    }
}
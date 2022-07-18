package com.tuocwizards.bgrem.pages

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tuocwizards.bgrem.R
import com.tuocwizards.bgrem.databinding.BottomDialogWindowBinding
import com.tuocwizards.bgrem.databinding.MainPageBinding
import com.tuocwizards.bgrem.types.MediaType

class MainPage: Fragment(R.layout.main_page) {

    private lateinit var binding: MainPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainPageBinding.inflate(inflater, container, false)
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

    private val getAction = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        binding.logo.load(it.data!!.data.toString())
        //findNavController().navigate(R.id.action_mainPage_to_selectBackgroundPage)
    }

    private fun showBottomDialog(mediaType: MediaType) {
        val bottomSheetDialog = BottomSheetDialog(context!!)

        val bottomSheetView: View = layoutInflater
            .inflate(R.layout.bottom_dialog_window, requireActivity().findViewById(R.id.bottom_dialog_container))

        BottomDialogWindowBinding.bind(bottomSheetView).apply {
            if (mediaType == MediaType.PHOTO) {
                noteTitle.isVisible = false
                noteText.isVisible = false
                cameraButton.setOnClickListener {
                    SelectBackgroundPage().getAction.launch("image/*")
                    //getAction.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
                    bottomSheetDialog.dismiss()
                }
                galleryButton.setOnClickListener {
                    //from conductor
                    //val intent = Intent(Intent.ACTION_GET_CONTENT).setType("image/*")
                    //from gallery


//                    try {
//                        SelectBackgroundPage().getAction.launch("image/*")
//                    } catch (e: Exception) {
//                        Log.e("TTT", e.toString())
//                    }
                    val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    getAction.launch(intent)
                    bottomSheetDialog.dismiss()
                }
            } else {
                cameraButton.setOnClickListener {
                    getAction.launch(Intent(MediaStore.ACTION_VIDEO_CAPTURE))
                    bottomSheetDialog.dismiss()
                }
                galleryButton.setOnClickListener {
                    val intent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
                    getAction.launch(intent)
                    bottomSheetDialog.dismiss()
                }
            }
            cancelButton.setOnClickListener {
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }
    }
}
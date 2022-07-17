package com.tuocwizards.bgrem.pages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tuocwizards.bgrem.R
import com.tuocwizards.bgrem.databinding.MainPageBinding
import java.lang.Exception

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
            photoButton.setOnClickListener { showBottomDialog() }
            videoButton.setOnClickListener { showBottomDialog() }
        }
        return binding.root
    }

    private fun goToInfoPage() {
        try {
            findNavController().navigate(R.id.action_mainPage_to_infoPage)
        }
        catch (e: Exception){
            Log.e("Nav", e.message!!)
        }
    }

    private fun showBottomDialog() {
            val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(context!!)
            val bottomSheetView: View = layoutInflater
                .inflate(R.layout.bottom_dialog_window, requireActivity().findViewById(R.id.bottom_dialog_container))

            bottomSheetView.findViewById<Button>(R.id.cancel_button).setOnClickListener{
                Toast.makeText(
                    context!!, "Cancel", Toast.LENGTH_SHORT
                ).show()
            }
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
    }
}
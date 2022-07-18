package com.tuocwizards.bgrem.pages

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import coil.load
import com.tuocwizards.bgrem.databinding.SelectBackgroundPageBinding


class SelectBackgroundPage : Fragment() {

    private lateinit var binding: SelectBackgroundPageBinding

    val getAction : ActivityResultLauncher<String> = registerForActivityResult(ActivityResultContracts.GetContent()) { imgUri: Uri? ->
        binding.photo.load(imgUri)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = SelectBackgroundPageBinding.inflate(inflater, container, false)

        return binding.root
    }
//
//    companion object {
//        val getAction : ActivityResultLauncher<String> = registerForActivityResult(ActivityResultContracts.GetContent()) { imgUri: Uri? ->
//            binding.photo.load(imgUri)
//        }
//    }

}
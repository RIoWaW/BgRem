package com.tuocwizards.bgrem.pages

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tuocwizards.bgrem.R
import com.tuocwizards.bgrem.databinding.SplashPageBinding

class SplashPage : Fragment() {

    private val appPreferences = "settings"
    private lateinit var binding: SplashPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SplashPageBinding.inflate(inflater, container, false)
        val isUserAgreeWithConditions = context!!.getSharedPreferences(appPreferences, Context.MODE_PRIVATE)
            .getBoolean("isUserAgreeWithConditions", false)
        if (isUserAgreeWithConditions) {
            goToNextPageWithAnimation(R.id.action_splashPage_to_mainPage)
        }
        else {
            goToNextPageWithAnimation(R.id.action_splashPage_to_startPage)
        }

        return binding.root
    }

    private fun goToNextPageWithAnimation(actionId: Int) = binding.apply {
        rem.post {
            rem.animate()
                .translationX(bg.width.toFloat())
                .setDuration(2000)
                .setStartDelay(400)
                .start()
            bg.animate()
                .translationX(-rem.width.toFloat())
                .setDuration(2000)
                .setStartDelay(400)
                .withEndAction {
                    goByAction(actionId)
                }
                .start()
        }
    }

    private fun goByAction(actionId: Int) {
        findNavController().navigate(actionId)
    }

}
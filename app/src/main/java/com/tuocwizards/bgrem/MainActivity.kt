package com.tuocwizards.bgrem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tuocwizards.bgrem.models.repositories.exchangebackground.BackgroundAPI
import com.tuocwizards.bgrem.pages.StartPage
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var backgroundAPI: BackgroundAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.empty_layout)
    }

}
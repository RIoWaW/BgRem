package com.tuocwizards.bgrem.di

import com.tuocwizards.bgrem.models.repositories.exchange.background.BackgroundAPI
import com.tuocwizards.bgrem.models.repositories.job.MediaModelAPI
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface MainComponent {
    val backgroundAPI: BackgroundAPI
    val mediaModelAPI: MediaModelAPI
}

@Module
class AppModule {

    @Provides
    fun provideMediaModelAPI(retrofit: Retrofit): MediaModelAPI {
        return retrofit.create(MediaModelAPI::class.java)
    }

    @Provides
    fun provideBackgroundAPI(retrofit: Retrofit): BackgroundAPI {
        return retrofit.create(BackgroundAPI::class.java)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dev.bgrem.deelvin.com/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}
package com.khue.calorietracker.tracker.tracker_data.di

import android.app.Application
import androidx.room.Room
import com.khue.calorietracker.tracker.tracker_data.local.TrackerDatabase
import com.khue.calorietracker.tracker.tracker_data.remote.OpenFoodApi
import com.khue.calorietracker.tracker.tracker_data.repository.TrackerRepositoryImpl
import com.khue.calorietracker.tracker.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenFoodApi(okHttpClient: OkHttpClient): OpenFoodApi {
        return Retrofit.Builder()
            .baseUrl(OpenFoodApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(OpenFoodApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTrackerDatabase(app: Application): TrackerDatabase {
        return Room.databaseBuilder(app, TrackerDatabase::class.java, "tracker_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTrackerRepository(
        trackerDatabase: TrackerDatabase,
        openFoodApi: OpenFoodApi
    ): TrackerRepository {
        return TrackerRepositoryImpl(trackerDatabase.dao, openFoodApi)
    }
}
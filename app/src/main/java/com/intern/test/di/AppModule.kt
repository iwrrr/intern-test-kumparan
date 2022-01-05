package com.intern.test.di

import com.intern.test.BuildConfig
import com.intern.test.data.repositories.KumparanRepository
import com.intern.test.data.repositories.KumparanRepositoryImpl
import com.intern.test.data.source.RemoteDataSource
import com.intern.test.data.source.RemoteDataSourceImpl
import com.intern.test.data.source.api.ApiService
import com.intern.test.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        )

        val client = OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        apiService: ApiService
    ) = RemoteDataSourceImpl(apiService) as RemoteDataSource

    @Provides
    @Singleton
    fun provideKumparanRepositoryImpl(
        remoteDataSource: RemoteDataSource
    ) = KumparanRepositoryImpl(remoteDataSource) as KumparanRepository
}
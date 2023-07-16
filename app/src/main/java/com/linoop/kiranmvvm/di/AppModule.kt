package com.linoop.kiranmvvm.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.linoop.kiranmvvm.Constants.BASE_URL
import com.linoop.kiranmvvm.Constants.MY_DATABASE_NAME
import com.linoop.kiranmvvm.Constants.MY_PREF
import com.linoop.kiranmvvm.repository.data.localdatabse.MyDao
import com.linoop.kiranmvvm.repository.data.localdatabse.MyDatabase
import com.linoop.kiranmvvm.repository.data.localdatabse.SharedPrefManager
import com.linoop.kiranmvvm.repository.data.networkapi.MyWebApi
import com.linoop.kiranmvvm.repository.data.networkapi.UrlInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMyDao(@ApplicationContext app: Context): MyDao =
        Room.databaseBuilder(app, MyDatabase::class.java, MY_DATABASE_NAME)
            //.addMigrations(MIGRATION_1_2)
            .build()
            .getDao()


    @Singleton
    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient): MyWebApi =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
            .create(MyWebApi::class.java)

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            //.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    @Singleton
    @Provides
    fun provideUrlInterceptor(sharedPrefManager: SharedPrefManager): Interceptor =
        UrlInterceptor(sharedPrefManager)

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context): SharedPrefManager {
        val sharePref = app.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE)
        return SharedPrefManager(sharePref)
    }

}
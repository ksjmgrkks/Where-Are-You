package com.android.whereareyou.core.data.di

import com.android.whereareyou.core.data.api.WhereAreYouApi
import com.android.whereareyou.core.data.api.adapter.CustomCallAdapterFactory
import com.android.whereareyou.core.data.api.interceptor.LoggingInterceptor
import com.android.whereareyou.core.data.api.interceptor.NetworkStatusInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val WHERE_ARE_YOU_BASE_URL = ""

    @Provides
    @Singleton
    fun provideWhereAreYouService(@WhereAreYouServer builder: Retrofit.Builder): WhereAreYouApi =
        builder.build().create(WhereAreYouApi::class.java)

    @Provides
    @Singleton
    @WhereAreYouServer
    fun provideWhereAreYouRetrofit(@WhereAreYouServer okHttpClient: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(WHERE_ARE_YOU_BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(CustomCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())

    @Provides
    @Singleton
    @WhereAreYouServer
    fun provideWhereAreYouOkHttpClient(
        @WhereAreYouServer interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        networkStatusInterceptor: NetworkStatusInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(networkStatusInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    @WhereAreYouServer
    fun provideWhereAreYouInterceptor(): Interceptor =
        Interceptor { chain ->
            with(chain) {
                proceed(
                    request()
                        .newBuilder()
                        .build()
                )
            }
        }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(loggingInterceptor: LoggingInterceptor): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(loggingInterceptor)
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}
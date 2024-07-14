package com.yessorae.data.di

import com.yessorae.data.BuildConfig
import com.yessorae.data.source.network.kakao.api.KakaoImageSearchApi
import com.yessorae.data.source.network.kakao.common.KakaoApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideKakaoOkHttpCallFactory(): Call.Factory =
        OkHttpClient.Builder()
            .addInterceptor(
                Interceptor { chain ->
                    val originalRequest = chain.request()
                    val newRequest = originalRequest.newBuilder()
                        .header(
                            name = "Authorization",
                            value = "${KakaoApiConstants.HEADER_KEY} 0062f18a02ed3a27e51fdca43de6cb1f"
                        )
                        .build()
                    chain.proceed(newRequest)
                }
            )
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    }
            )
            .build()

    @Provides
    @Singleton
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun provideKakaoApiRetrofit(
        okhttpCallFactory: Call.Factory,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(KakaoApiConstants.API_BASE_URL)
            .callFactory(okhttpCallFactory)
            .addConverterFactory(
                json.asConverterFactory("application/json; charset=utf-8".toMediaType())
            )
            .build()

    @Provides
    @Singleton
    fun provideKakaoImageSearchApi(retrofit: Retrofit): KakaoImageSearchApi = retrofit.create(KakaoImageSearchApi::class.java)
}

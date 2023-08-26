package com.example.moviego.di

import com.example.moviego.data.source.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val TIME_OUT = 30L
const val BASE_URL: String = "https://api.themoviedb.org/3/movie/"
const val IMAGE_URL: String = "https://image.tmdb.org/t/p/w500"

const val token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwOWY1ODExYzcyYWYwNmUyNjc3YmIwM2U2ZDIzZmU2MyIsInN1YiI6IjY0ZTllNTcwZTg5NGE2MDExZWY4OTBjYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.DOAVfOVdM5U7PGT6scpn0D4O92N5LJ0rNv-ThaW7JjU"
val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), BASE_URL) }

    single { createOkHttpClient() }



}

//    fun createPostRepository(apiService: ApiService): PostsRepository {
//        return PostsRepositoryImp(apiService)
//    }
//
//    fun createGetPostsUseCase(postsRepository: PostsRepository): GetPostsUseCase {
//        return GetPostsUseCase(postsRepository)
//    }
fun createOkHttpClient(): OkHttpClient {


    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
         .addInterceptor(httpLoggingInterceptor)

        .addInterceptor { chain ->
            val originalRequest = chain.request()

            val request = originalRequest.newBuilder()
                .apply {
                    header("Accept-Language", "en")
                    header("Authorization", "Bearer $token")
                }
                .build()

            chain.proceed(request)
        }
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    val gson: Gson = GsonBuilder().create()

    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)

}


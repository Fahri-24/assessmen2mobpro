package org.d3if0064.assessment2.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0064.assessment2.model.Copyright
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/Fahri-24/static-api/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CopyrightApiService {
    @GET("static-api.json")
    suspend fun getCopyright(): List<Copyright>
}

object CopyrightApi {
    val service: CopyrightApiService by lazy {
        retrofit.create(CopyrightApiService::class.java)
    }
    fun getCopyrightUrl(nama: String): String {
        return "$BASE_URL$nama.png"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }
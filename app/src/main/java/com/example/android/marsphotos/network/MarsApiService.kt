
package com.example.android.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

/**
 * Buat objek Moshi yang akan digunakan Retrofit, pastikan untuk menambahkan adaptor Kotlin
 * Kompatibilitas penuh Kotlin.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Gunakan pembuat Retrofit untuk membuat objek retrofit menggunakan konverter Moshi dengan Moshi kami
 * objek.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * Antarmuka publik yang menampilkan metode [getPhotos].
 */
interface MarsApiService {
    /**
     * Mengembalikan [Daftar] dari [MarsPhoto] dan metode ini dapat dipanggil dari Coroutine.
     * Anotasi @GET menunjukkan bahwa titik akhir "foto" akan diminta dengan GET
     * Metode HTTP
     */
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}

/**
 * Objek Api publik yang memperlihatkan layanan Retrofit yang diinisialisasi lambat
 */
object MarsApi {
    val retrofitService: MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}

/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//enum to define constants that match query values
enum class MarsApiFilter(val value: String){
    SHOW_RENT("rent"),
    SHOW_BUY("buy"),
    SHOW_ALL("all")
}

//Base Url
private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

//Moshi
private val moshi = Moshi.Builder()
        //Moshi annotation prepocesser
        .add(KotlinJsonAdapterFactory())
        .build()

//Retrofit
private  val retrofit = Retrofit.Builder()
        //Converter Factory
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        //Base Url
        .baseUrl(BASE_URL)
        .build()

//Interface that determines how Retrofit communicates with web server
interface MarsApiService {
    @GET("realestate")
    suspend fun  getProperties(@Query("filter") type: String): List<MarsProperty>
}


//Object to initialize the Api
object MarsApi{
    val retrofitService: MarsApiService by  lazy {
        retrofit.create(MarsApiService::class.java)
    }
}
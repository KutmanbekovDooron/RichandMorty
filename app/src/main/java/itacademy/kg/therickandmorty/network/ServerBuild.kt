package itacademy.kg.therickandmorty.network

import itacademy.kg.therickandmorty.model.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServerBuild {

    fun provideRetrofit():networkUtils{
        val retrofit = Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(networkUtils::class.java)
    }

}
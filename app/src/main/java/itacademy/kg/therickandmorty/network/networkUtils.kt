package itacademy.kg.therickandmorty.network

import android.location.Location
import itacademy.kg.therickandmorty.databinding.FragmentCharactersBinding
import itacademy.kg.therickandmorty.model.character.Characters
import itacademy.kg.therickandmorty.model.character.InformationCharatcters
import itacademy.kg.therickandmorty.model.episode.Episode
import itacademy.kg.therickandmorty.model.location.LocationPlace
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface networkUtils {
    @GET("character")
    suspend fun getCharakter(
        @Query("name") name : String,
        @Query("status") status : String,
        @Query("species") species : String,
        @Query("gender") gender : String
    ) : Response<Characters>
    @GET("location")
    suspend fun getLocation(
        @Query("name") name : String,
        @Query("type") type : String,
        @Query("dimension") dimension : String,
    ) : Response<LocationPlace>

    @GET("episode")
    suspend fun getEpisode(
        @Query("name") name :String,
        @Query("episode") episode  :String,
    ) : Response<Episode>

    @GET("episode/{id}")
    suspend fun getOneEpisode(
        @Query("id") id : String
    ) : Response<Episode>


}
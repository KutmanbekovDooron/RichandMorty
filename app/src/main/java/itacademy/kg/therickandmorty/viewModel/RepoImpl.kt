package itacademy.kg.therickandmorty.viewModel

import android.location.Location
import itacademy.kg.therickandmorty.model.character.Characters
import itacademy.kg.therickandmorty.model.episode.Episode
import itacademy.kg.therickandmorty.model.location.LocationPlace
import retrofit2.Response

interface RepoImpl {
    suspend fun getCurrentCharacters(name: String,status : String, species:String,gender:String) : Response<Characters>
    suspend fun getCurrentlocation(name:String,type:String,dismension:String) : Response<LocationPlace>
    suspend fun getCurrentEpisode(name:String,episode:String) : Response<Episode>
    suspend fun getOneEpisode( id : String)  :Response<Episode>
}
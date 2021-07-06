package itacademy.kg.therickandmorty.viewModel

import android.location.Location
import android.renderscript.Short4
import itacademy.kg.therickandmorty.model.character.Characters
import itacademy.kg.therickandmorty.model.episode.Episode
import itacademy.kg.therickandmorty.model.location.LocationPlace
import itacademy.kg.therickandmorty.network.ServerBuild
import retrofit2.Response

class Repository () : RepoImpl {
    override suspend fun getCurrentCharacters(
        name: String,
        status: String,
        species: String,
        gender: String
    ): Response<Characters> {
        return ServerBuild.provideRetrofit().getCharakter(name, status, species, gender)
    }

    override suspend fun getCurrentlocation(
        name: String,
        type: String,
        dismension: String
    ): Response<LocationPlace> {
        return ServerBuild.provideRetrofit().getLocation(name,type,dismension)
    }

    override suspend fun getCurrentEpisode(name: String, episode: String): Response<Episode> {
        return ServerBuild.provideRetrofit().getEpisode(name, episode)
    }

    override suspend fun getOneEpisode(id: String): Response<Episode> {
        return ServerBuild.provideRetrofit().getOneEpisode(id)
    }


}
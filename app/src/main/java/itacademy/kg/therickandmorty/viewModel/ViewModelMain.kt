package itacademy.kg.therickandmorty.viewModel

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import itacademy.kg.therickandmorty.model.character.Characters
import itacademy.kg.therickandmorty.model.episode.Episode
import itacademy.kg.therickandmorty.model.location.LocationPlace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelMain () : ViewModel() {
    var liveDataForCharacters =  MutableLiveData<Characters>()
    var liveDataForLocation =  MutableLiveData<LocationPlace>()
    var liveDataForEpisode =  MutableLiveData<Episode>()
    var liveDataOneEpisode = MutableLiveData<Episode>()
    var repository = Repository()

    init {
        getCharacter("","","","")
        getLocation("","","")
    }

    fun getCharacter(name: String,status:String,species:String,gender:String){
        viewModelScope.launch (Dispatchers.IO){
            val response = repository.getCurrentCharacters(name,status,species,gender)
            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    liveDataForCharacters.value = response.body()!!
                }
            }
        }
    }fun getLocation(name: String,type: String,dimension:String){
        viewModelScope.launch (Dispatchers.IO){
            val response = repository.getCurrentlocation(name,type,dimension)
            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    liveDataForLocation.value = response.body()!!
                }
            }
        }
    }
    fun getEpisode(name: String,episode:String){
        viewModelScope.launch (Dispatchers.IO){
            val response = repository.getCurrentEpisode(name,episode)
            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    liveDataForEpisode.value = response.body()!!
                }
            }
        }
    }
    fun getOneEpisode(id : String){
        viewModelScope.launch (Dispatchers.IO){
            val response = repository.getOneEpisode(id)
            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    liveDataOneEpisode.value = response.body()!!
                }
            }
        }
    }


}
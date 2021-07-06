package itacademy.kg.therickandmorty.model.character

import itacademy.kg.therickandmorty.model.character.LocationCharacters
import itacademy.kg.therickandmorty.model.character.OrginalCharacters
import java.io.Serializable

data class ResultsCharacters (val id : Int,
                              val name : String,
                              val status : String,
                              val species : String,
                              val type : String,
                              val gender : String,
                              val image:String,
                              val origin : OrginalCharacters,
                              val location: LocationCharacters,
                              val episode : ArrayList<String>,
                              val url : String,
                              val created : String
) : Serializable

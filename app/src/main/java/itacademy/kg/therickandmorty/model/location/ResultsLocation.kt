package itacademy.kg.therickandmorty.model.location

data class ResultsLocation(
    val id : Int,
    val name:String,
    val type:String,
    val dimension:String,
    val residents:ArrayList<String>,
    val url : String,
    val created:String
)

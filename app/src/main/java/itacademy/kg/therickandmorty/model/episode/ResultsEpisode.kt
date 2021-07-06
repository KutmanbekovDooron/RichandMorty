package itacademy.kg.therickandmorty.model.episode

data class ResultsEpisode (
    val id:Int,
    val name:String,
    val air_date:String,
    val episode:String,
    val characters:ArrayList<String>,
    val url:String,
    val created:String
    )

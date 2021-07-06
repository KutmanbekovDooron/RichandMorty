package itacademy.kg.therickandmorty.model.episode

data class Episode(
    val info:InformationEpisode,
    val results:MutableList<ResultsEpisode>
)
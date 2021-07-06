package itacademy.kg.therickandmorty.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import itacademy.kg.therickandmorty.databinding.ItamEpisodeBinding
import itacademy.kg.therickandmorty.model.episode.Episode
import itacademy.kg.therickandmorty.model.episode.ResultsEpisode

class AdapterEpisode(list: MutableList<ResultsEpisode>, var temp : Boolean, testList: MutableList<String>) : RecyclerView.Adapter<AdapterEpisode.EpisodeHolder>() {
    inner class EpisodeHolder(val binding : ItamEpisodeBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    val listEpisode = list
    val string = testList


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeHolder {
        val binding = ItamEpisodeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EpisodeHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeHolder, position: Int) {

        Log.i("myTest",string.toString())
        Log.i("myTest",listEpisode.toString())

        if (temp) {
        val position = listEpisode.get(position)
            with(holder) {
                binding.apply {
                    nameEpis.text = position.name
                }
            }
        }else{
            val positionString = string.get(position)
            Log.i("myA",positionString)
            with(holder){
                binding.apply {
                    nameEpis.text = positionString
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listEpisode.size
    }
}
package itacademy.kg.therickandmorty.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import itacademy.kg.therickandmorty.databinding.FragmentLocationBinding
import itacademy.kg.therickandmorty.databinding.ItamLocationBinding
import itacademy.kg.therickandmorty.model.location.ResultsLocation

class AdapterLocation(list: MutableList<ResultsLocation>) :
    RecyclerView.Adapter<AdapterLocation.LocationHolder>() {

    inner class LocationHolder(val binding: ItamLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    val listLocation = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationHolder {
        val binding =
            ItamLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationHolder, position: Int) {
        with(holder) {
            binding.apply {
                val position = listLocation.get(position)
                nameLoca.text = position.name
                typeLoca.text = position.type
                dimensionLoca.text = position.dimension
            }
        }
    }

    override fun getItemCount(): Int {
        return listLocation.size
    }
}
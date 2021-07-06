package itacademy.kg.therickandmorty.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import itacademy.kg.therickandmorty.R
import itacademy.kg.therickandmorty.adapters.AdapterEpisode
import itacademy.kg.therickandmorty.adapters.EpisodeLinkAdapter
import itacademy.kg.therickandmorty.databinding.FragmentCharactersBinding
import itacademy.kg.therickandmorty.databinding.FragmentDetelisBinding
import itacademy.kg.therickandmorty.model.character.ResultsCharacters
import itacademy.kg.therickandmorty.model.episode.ResultsEpisode

class DetelisFragment : Fragment() {
    private var _binding: FragmentDetelisBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : AdapterEpisode

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetelisBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        val char = bundle?.getSerializable("info") as ResultsCharacters

        binding.apply {
            Glide.with(this@DetelisFragment).load(char.image).into(imageView)
            statusDete.text = char.status
            nameDete.text = char.name
            spicesDete.text = char.species
            locationDete.text = char.origin.name
            location2Dete.text = char.location.name
        }
//        val char1 = mutableListOf<ResultsEpisode>()
//        configureRecyclerView(char1,binding.adapterLink,char.episode)
    }

//    private fun configureRecyclerView(list: MutableList<ResultsEpisode>, recycle: RecyclerView, string : MutableList<String>) {
//        binding.apply {
//            Log.i("myE",string.toString())
//            recycle.layoutManager =
//                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            recycle.setHasFixedSize(true)
//            adapter = AdapterEpisode(list,false, string)
//            recycle.adapter = adapter
//        }
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
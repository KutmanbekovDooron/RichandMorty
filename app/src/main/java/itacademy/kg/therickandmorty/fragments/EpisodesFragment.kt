package itacademy.kg.therickandmorty.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import itacademy.kg.therickandmorty.R
import itacademy.kg.therickandmorty.adapters.AdapterEpisode
import itacademy.kg.therickandmorty.adapters.AdapterLocation
import itacademy.kg.therickandmorty.databinding.FragmentEpisodesBinding
import itacademy.kg.therickandmorty.model.episode.ResultsEpisode
import itacademy.kg.therickandmorty.model.location.ResultsLocation
import itacademy.kg.therickandmorty.viewModel.ViewModelMain

class EpisodesFragment : Fragment() {

    private var _binding:FragmentEpisodesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter : AdapterEpisode
    private var viewModelMain = ViewModelMain()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelMain = ViewModelProvider(this).get(ViewModelMain::class.java)


        viewModelMain.getEpisode("","")
        viewModelMain.liveDataForEpisode.observe(viewLifecycleOwner){
            binding.apply {
                recycleEpisode.visibility = View.VISIBLE
                progressBarEpisode.visibility = View.GONE
            }

            configureRecyclerView(it.results,binding.recycleEpisode)
        }
        binding.fagEpisode.setOnClickListener {
            alearDilogeShow()
        }
    }

    private fun alearDilogeShow() {
        val dilog = AlertDialog.Builder(context)
            .setTitle("Filtr")
            .setIcon(R.drawable.ic_baseline_filter_list_24)

        val view = LayoutInflater.from(context).inflate(R.layout.filter_episode,null)
        val name = view.findViewById<EditText>(R.id.name_filtr_episode)
        val episode = view.findViewById<EditText>(R.id.episode_filtr_episode)


        dilog.setView(view)
            .setPositiveButton("selected"){_, _ ->

            }
            .setNegativeButton("cancel"){_, _ ->

            }

    }

    private fun configureRecyclerView(list: MutableList<ResultsEpisode>, recycle: RecyclerView) {
        binding.apply {
            recycle.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recycle.setHasFixedSize(true)
            val string = mutableListOf<String>()
            adapter = AdapterEpisode(list,true, string)
            recycle.adapter = adapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
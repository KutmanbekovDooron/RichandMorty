package itacademy.kg.therickandmorty.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import itacademy.kg.therickandmorty.R
import itacademy.kg.therickandmorty.adapters.AdapterCharacters
import itacademy.kg.therickandmorty.adapters.AdapterLocation
import itacademy.kg.therickandmorty.databinding.FragmentLocationBinding
import itacademy.kg.therickandmorty.model.character.ResultsCharacters
import itacademy.kg.therickandmorty.model.location.ResultsLocation
import itacademy.kg.therickandmorty.viewModel.ViewModelMain

class LocationFragment : Fragment() {

    private var _binding : FragmentLocationBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel : ViewModelMain
    lateinit var adapter: AdapterLocation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModelMain::class.java)

        viewModel.liveDataForLocation.observe(viewLifecycleOwner){
            binding.apply {
                recycleLocation.visibility = View.VISIBLE
                progressBarLocation.visibility = View.GONE
            }
            configureRecyclerView(it.results,binding.recycleLocation)
        }

        binding.fagLocation.setOnClickListener {
            alearDilogShow()
        }
    }

    private fun alearDilogShow() {
        val dilog = AlertDialog.Builder(context)
            .setTitle("Filtr")
            .setIcon(R.drawable.ic_baseline_filter_list_24)

        val view = LayoutInflater.from(context).inflate(R.layout.filtr_location,null)

        dilog.setView(view)
            .setPositiveButton("selected"){ _, _ ->

            }
            .setNegativeButton("cancel"){_, _ ->

            }
    }

    private fun configureRecyclerView(list: MutableList<ResultsLocation>, recycle: RecyclerView) {
        binding.apply {
            recycle.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recycle.setHasFixedSize(true)
            adapter = AdapterLocation(list)
            recycle.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
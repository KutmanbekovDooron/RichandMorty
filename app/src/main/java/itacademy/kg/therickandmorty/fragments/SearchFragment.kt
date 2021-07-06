package itacademy.kg.therickandmorty.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import itacademy.kg.therickandmorty.R
import itacademy.kg.therickandmorty.adapters.AdapterCharacters
import itacademy.kg.therickandmorty.adapters.AdapterEpisode
import itacademy.kg.therickandmorty.adapters.AdapterLocation
import itacademy.kg.therickandmorty.adapters.onClickChcracter
import itacademy.kg.therickandmorty.databinding.FragmentSearchBinding
import itacademy.kg.therickandmorty.model.character.ResultsCharacters
import itacademy.kg.therickandmorty.model.episode.ResultsEpisode
import itacademy.kg.therickandmorty.model.location.ResultsLocation
import itacademy.kg.therickandmorty.viewModel.ViewModelMain
import java.sql.ResultSet
import javax.xml.transform.Result

class SearchFragment : Fragment(), onClickChcracter {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ViewModelMain

    private var selectedItamSpinner = ""
    private var status = ""
    private var gender = ""
    private var type = ""
    private var dimension = ""
    private var specices = ""

    private lateinit var adapterSpinner: ArrayAdapter<String>
    lateinit var adapterCharacter: AdapterCharacters
    lateinit var adapterLocation: AdapterLocation
    lateinit var adapterEpisode: AdapterEpisode

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewModelMain::class.java)

        viewModel.liveDataForCharacters.observe(viewLifecycleOwner) {
            configureRecyclerView1(it.results, binding.recycleSearch)
        }

        viewModel.liveDataForLocation.observe(viewLifecycleOwner) {
            configureRecyclerView2(it.results, binding.recycleSearch)
        }
        viewModel.liveDataForEpisode.observe(viewLifecycleOwner) {
            configureRecyclerView3(it.results, binding.recycleSearch)
        }

        sendSearchResponse()

        var list = mutableListOf<String>()
        list.add("")
        list.add("Charecters")
        list.add("Location")
        list.add("Episode")


        adapterSpinner = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinner.adapter = adapterSpinner

        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedItamSpinner = parent?.selectedItem.toString()
                Log.i("myS", selectedItamSpinner)
                if (selectedItamSpinner.equals("Charecters")) {
                    openAddDialogChar()
                } else if (selectedItamSpinner.equals("Location")) {
                    openDialogLoc()
                } else if (selectedItamSpinner.equals("Episode")) {

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.searchText.setOnClickListener {
            Toast.makeText(context, "Search button clicked", Toast.LENGTH_SHORT).show()
            if (selectedItamSpinner == "Episode") {
                viewModel.getEpisode(binding.searchEdit.text.toString(), "")
            } else if (selectedItamSpinner == "Charecters") {
                Log.i(
                    "MyB",
                    binding.searchEdit.text.toString() + gender.toString() + status.toString()
                )
                viewModel.getCharacter(binding.searchEdit.text.toString(), status, specices, gender)
            } else if (selectedItamSpinner == "Location") {
                viewModel.getLocation(binding.searchEdit.text.toString(), type, dimension)


            }

        }

    }

    private fun openAddDialogChar() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Choose params")
            .setMessage("Please choose all params you need")
            .setIcon(R.drawable.ic_baseline_filter_list_24)


        val view = LayoutInflater.from(context).inflate(R.layout.filter_selected, null)
        dialog.setView(view)
        dialog.setPositiveButton("Choose params") { _, _ ->

            if (view.findViewById<RadioButton>(R.id.alive).isChecked) {
                status = view.findViewById<RadioButton>(R.id.alive).text.toString()
            } else if (view.findViewById<RadioButton>(R.id.dead).isChecked) {
                status = view.findViewById<RadioButton>(R.id.dead).text.toString()
            } else if (view.findViewById<RadioButton>(R.id.unknown).isChecked) {
                status = view.findViewById<RadioButton>(R.id.unknown).text.toString()
            }
            if (view.findViewById<RadioButton>(R.id.male).isChecked) {
                gender = view.findViewById<RadioButton>(R.id.male).text.toString()
            } else if (view.findViewById<RadioButton>(R.id.female).isChecked) {
                gender = view.findViewById<RadioButton>(R.id.female).text.toString()
            } else if (view.findViewById<RadioButton>(R.id.genderless).isChecked) {
                gender = view.findViewById<RadioButton>(R.id.genderless).text.toString()
            } else if (view.findViewById<RadioButton>(R.id.unknownGen).isChecked) {
                gender = view.findViewById<RadioButton>(R.id.unknownGen).text.toString()
            }

            specices = view.findViewById<EditText>(R.id.filtr_species).text.toString()
        }
        dialog.setNegativeButton("Cancel") { _, _ ->

        }
        dialog.show()
    }


    private fun openDialogLoc() {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Choose params")
            .setMessage("Please choose all params you need")
            .setIcon(R.drawable.ic_baseline_filter_list_24)


        val view = LayoutInflater.from(context).inflate(R.layout.filtr_location, null)
        dialog.setView(view)
        dialog.setPositiveButton("Choose params") { _, _ ->
            if (view.findViewById<EditText>(R.id.type_filtr_location).text.toString().isNotEmpty()
                && view.findViewById<EditText>(R.id.dimension_filtr_location).text.toString().isNotEmpty()) {
                type = view.findViewById<EditText>(R.id.type_filtr_location).text.toString()
                dimension =
                    view.findViewById<EditText>(R.id.dimension_filtr_location).text.toString()
            }
        }
        dialog.setNegativeButton("Cancel") { _, _ ->

        }
        dialog.show()
    }

    private fun sendSearchResponse() {


    }

    private fun configureRecyclerView1(
        list: MutableList<ResultsCharacters>,
        recycle: RecyclerView
    ) {
        binding.apply {
            recycle.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recycle.setHasFixedSize(true)
            adapterCharacter = AdapterCharacters(list, this@SearchFragment, requireContext())
            recycle.adapter = adapterCharacter
        }
    }

    private fun configureRecyclerView2(list: MutableList<ResultsLocation>, recycle: RecyclerView) {
        binding.apply {
            recycle.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recycle.setHasFixedSize(true)
            adapterLocation = AdapterLocation(list)
            recycle.adapter = adapterLocation
        }

    }

    private fun configureRecyclerView3(list: MutableList<ResultsEpisode>, recycle: RecyclerView) {
        binding.apply {
            recycle.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recycle.setHasFixedSize(true)
            val string = mutableListOf<String>()
            adapterEpisode = AdapterEpisode(list, true, string)
            recycle.adapter = adapterEpisode
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClickCharacterPosition(position: Int) {
        TODO("Not yet implemented")
    }

}
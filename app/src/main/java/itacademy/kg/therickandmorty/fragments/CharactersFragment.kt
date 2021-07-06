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
import com.google.android.material.navigation.NavigationView
import itacademy.kg.therickandmorty.R
import itacademy.kg.therickandmorty.adapters.AdapterCharacters
import itacademy.kg.therickandmorty.adapters.onClickChcracter
import itacademy.kg.therickandmorty.databinding.FragmentCharactersBinding
import itacademy.kg.therickandmorty.model.character.ResultsCharacters
import itacademy.kg.therickandmorty.viewModel.ViewModelMain

class CharactersFragment : Fragment(), onClickChcracter{

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private var viewModel = ViewModelMain()
    private lateinit var adapter: AdapterCharacters
    lateinit var listChar: MutableList<ResultsCharacters>
//    lateinit var adapterList:ArrayAdapter<String>
//    lateinit var adapterList2: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ViewModelMain::class.java)
        listChar = mutableListOf()

        viewModel.liveDataForCharacters.observe(viewLifecycleOwner) {
            Log.i("myTag", it.results.toString())
            binding.apply {
                recycleCharacter.visibility = View.VISIBLE
                fag.visibility = View.VISIBLE
                progressBarCharacter.visibility = View.GONE
            }

            configureRecyclerView(it.results, binding.recycleCharacter)
        }


    }

    private fun configureRecyclerView(list: ArrayList<ResultsCharacters>, recycle: RecyclerView) {
        listChar.addAll(list)
        binding.apply {
            recycle.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recycle.setHasFixedSize(true)
            adapter = AdapterCharacters(list, this@CharactersFragment, requireContext())
            recycle.adapter = adapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickCharacterPosition(position: Int) {
        val bundle = Bundle()
        bundle.putSerializable("info", listChar.get(position))

        val fragment = DetelisFragment()
        fragment.arguments = bundle

        setFragment(fragment)
    }

    private fun setFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
            commit()
        }
    }


}
package itacademy.kg.therickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import itacademy.kg.therickandmorty.databinding.ActivityMainBinding
import itacademy.kg.therickandmorty.fragments.CharactersFragment
import itacademy.kg.therickandmorty.fragments.EpisodesFragment
import itacademy.kg.therickandmorty.fragments.LocationFragment
import itacademy.kg.therickandmorty.fragments.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(CharactersFragment())

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.characters -> setFragment(CharactersFragment())
                R.id.location -> setFragment(LocationFragment())
                R.id.episod -> setFragment(EpisodesFragment())
                R.id.search -> setFragment(SearchFragment())
            }
            true
        }
    }

    private fun setFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment)
            commit()
        }
    }



}
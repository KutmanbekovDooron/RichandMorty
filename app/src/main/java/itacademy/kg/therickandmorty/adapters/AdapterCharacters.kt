package itacademy.kg.therickandmorty.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import itacademy.kg.therickandmorty.databinding.ItamCharatcterBinding
import itacademy.kg.therickandmorty.model.character.ResultsCharacters

class AdapterCharacters(list: MutableList<ResultsCharacters>,
                        val listiner : onClickChcracter,
                        val context : Context
) :
    RecyclerView.Adapter<AdapterCharacters.CharacterHolder>() {

    inner class CharacterHolder(val binding:ItamCharatcterBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                listiner.onClickCharacterPosition(adapterPosition)
            }
        }
    }

    val listChar = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder{
        var binding = ItamCharatcterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        with(holder){
           binding.apply {
                val position = listChar.get(position)
               nameChar.text = position.name
               statusChar.text = position.status
               speciesChar.text = position.species
               locationChar.text = position.origin.name
               location2Char.text = position.location.name
               Glide.with(context).load(position.image).into(imageChar)
           }
        }
    }

    override fun getItemCount(): Int {
        return listChar.size
    }

}

interface onClickChcracter{
    fun onClickCharacterPosition(position : Int)
}
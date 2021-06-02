package au.com.doshii.starwars.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import au.com.doshii.starwars.R
import au.com.doshii.starwars.model.Species
import au.com.doshii.starwars.view.fragment.ListFragmentDirections
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.android.synthetic.main.item_list.view.list_item_home
import java.util.ArrayList
import java.util.HashMap

class SpeciesListAdapter(private var species: ArrayList<Species>):
    RecyclerView.Adapter<SpeciesListAdapter.SpeciesViewHolder>() {
    class SpeciesViewHolder(var view: View):RecyclerView.ViewHolder(view)

    fun updateSpecies(newSpeciesResult: ArrayList<Species>) {
        species.clear()
        species.addAll(newSpeciesResult)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.item_list,parent,false)
        return SpeciesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        holder.view.listTitleText.text=species[position].name
        holder.view.list_item_home.setOnClickListener {
            val action = ListFragmentDirections.actionGotoDetailsFragment(getDetailsAsHaspMap(species[position]))

            Navigation.findNavController(holder.view).navigate(action)
        }
    }

    private fun getDetailsAsHaspMap(species: Species): HashMap<Any, Any> {
        val detailsHashMap = HashMap<Any, Any>()
        detailsHashMap.put("Name",species.name)
        detailsHashMap.put("Classification",species.classification)
        detailsHashMap.put("Designation",species.designation)
        detailsHashMap.put("Average Height",species.averageHeight)
        detailsHashMap.put("Skin Colors",species.skinColors)
        detailsHashMap.put("Hair Colors",species.hairColors)
        detailsHashMap.put("Eye Colors",species.eyeColors)
        detailsHashMap.put("Average Lifespan",species.averageLifespan)
        detailsHashMap.put("Homeworld",species.homeworld?.apply { this }.let { "N/A" })
        detailsHashMap.put("Language",species.language)
        detailsHashMap.put("Edited",species.edited)
        detailsHashMap.put("Created",species.created)
        detailsHashMap.put("Url",species.url)
       return detailsHashMap
    }

    override fun getItemCount()=species.size
}
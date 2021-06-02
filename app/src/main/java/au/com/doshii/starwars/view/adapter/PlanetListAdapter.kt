package au.com.doshii.starwars.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import au.com.doshii.starwars.R
import au.com.doshii.starwars.model.Planet
import au.com.doshii.starwars.view.fragment.ListFragmentDirections
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.android.synthetic.main.item_list.view.list_item_home
import java.util.ArrayList
import java.util.HashMap

class PlanetListAdapter(private var planets: ArrayList<Planet>):
    RecyclerView.Adapter<PlanetListAdapter.planetViewHolder>() {
    class planetViewHolder(var view: View):RecyclerView.ViewHolder(view)

    fun updatePlanet(newplanetResult: ArrayList<Planet>) {
        planets.clear()
        planets.addAll(newplanetResult)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): planetViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.item_list,parent,false)
        return planetViewHolder(view)
    }

    override fun onBindViewHolder(holder: planetViewHolder, position: Int) {
        holder.view.listTitleText.text=planets[position].name
        holder.view.list_item_home.setOnClickListener {
            val action = ListFragmentDirections.actionGotoDetailsFragment(getDetailsAsHaspMap(planets[position]))

            Navigation.findNavController(holder.view).navigate(action)
        }
    }

    private fun getDetailsAsHaspMap(planet: Planet): HashMap<Any, Any> {
        val detailsHashMap = HashMap<Any, Any>()
        detailsHashMap.put("Name",planet.name)
        detailsHashMap.put("Rotation Period",planet.rotationPeriod)
        detailsHashMap.put("Orbital Period",planet.orbitalPeriod)
        detailsHashMap.put("Diameter",planet.diameter)
        detailsHashMap.put("Climate",planet.climate)
        detailsHashMap.put("Gravity",planet.gravity)
        detailsHashMap.put("Terrain",planet.terrain)
        detailsHashMap.put("Surface Water",planet.surfaceWater)
        detailsHashMap.put("Population",planet.population)
        detailsHashMap.put("Created",planet.created)
        detailsHashMap.put("Edited",planet.edited)
        detailsHashMap.put("Url",planet.url)
       return detailsHashMap
    }

    override fun getItemCount()=planets.size
}
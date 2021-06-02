package au.com.doshii.starwars.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import au.com.doshii.starwars.R
import au.com.doshii.starwars.model.StarShip
import au.com.doshii.starwars.view.fragment.ListFragmentDirections
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.android.synthetic.main.item_list.view.list_item_home
import java.util.ArrayList
import java.util.HashMap

class StarShipListAdapter(private var starShips: ArrayList<StarShip>):
    RecyclerView.Adapter<StarShipListAdapter.StarShipViewHolder>() {
    class StarShipViewHolder(var view: View):RecyclerView.ViewHolder(view)

    fun updateStarShip(newStarShipResult: ArrayList<StarShip>) {
        starShips.clear()
        starShips.addAll(newStarShipResult)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarShipViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.item_list,parent,false)
        return StarShipViewHolder(view)
    }

    override fun onBindViewHolder(holder: StarShipViewHolder, position: Int) {
        holder.view.listTitleText.text=starShips[position].name
        holder.view.list_item_home.setOnClickListener {
            val action = ListFragmentDirections.actionGotoDetailsFragment(getDetailsAsHaspMap(starShips[position]))

            Navigation.findNavController(holder.view).navigate(action)
        }
    }

    private fun getDetailsAsHaspMap(starShip: StarShip): HashMap<Any, Any> {
        val detailsHashMap = HashMap<Any, Any>()
        detailsHashMap.put("Name",starShip.name)
        detailsHashMap.put("Model",starShip.model)
        detailsHashMap.put("Manufacturer",starShip.manufacturer)
        detailsHashMap.put("Cost In Credits",starShip.costInCredits)
        detailsHashMap.put("Length",starShip.length)
        detailsHashMap.put("Max Atmosphering Speed",starShip.maxAtmospheringSpeed)
        detailsHashMap.put("Crew",starShip.crew)
        detailsHashMap.put("Passengers",starShip.passengers)
        detailsHashMap.put("Cargo Capacity",starShip.cargoCapacity)
        detailsHashMap.put("Consumables",starShip.consumables)
        detailsHashMap.put("Hyperdrive Rating",starShip.hyperdriveRating)
        detailsHashMap.put("MGLT",starShip.mglt)
        detailsHashMap.put("Starship Class",starShip.starshipClass)
        detailsHashMap.put("Edited",starShip.edited)
        detailsHashMap.put("Created",starShip.created)
        detailsHashMap.put("Url",starShip.url)
       return detailsHashMap
    }

    override fun getItemCount()=starShips.size
}
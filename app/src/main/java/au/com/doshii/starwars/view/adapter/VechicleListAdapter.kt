package au.com.doshii.starwars.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import au.com.doshii.starwars.R
import au.com.doshii.starwars.model.Vehicle
import au.com.doshii.starwars.view.fragment.ListFragmentDirections
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.android.synthetic.main.item_list.view.list_item_home
import java.util.ArrayList
import java.util.HashMap

class VechicleListAdapter(private var vehicles: ArrayList<Vehicle>):
    RecyclerView.Adapter<VechicleListAdapter.VehicleViewHolder>() {
    class VehicleViewHolder(var view: View):RecyclerView.ViewHolder(view)

    fun updateVehicle(newVehicleResult: ArrayList<Vehicle>) {
        vehicles.clear()
        vehicles.addAll(newVehicleResult)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.item_list,parent,false)
        return VehicleViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.view.listTitleText.text=vehicles[position].name
        holder.view.list_item_home.setOnClickListener {
            val action = ListFragmentDirections.actionGotoDetailsFragment(getDetailsAsHaspMap(vehicles[position]))

            Navigation.findNavController(holder.view).navigate(action)
        }
    }

    private fun getDetailsAsHaspMap(vehicle: Vehicle): HashMap<Any, Any> {
        val detailsHashMap = HashMap<Any, Any>()
        detailsHashMap.put("Name",vehicle.name)
        detailsHashMap.put("Model",vehicle.model)
        detailsHashMap.put("Manufacturer",vehicle.manufacturer)
        detailsHashMap.put("Cost In Credits",vehicle.costInCredits)
        detailsHashMap.put("Length",vehicle.length)
        detailsHashMap.put("Max Atmosphering Speed",vehicle.maxAtmospheringSpeed)
        detailsHashMap.put("Crew",vehicle.crew)
        detailsHashMap.put("Passengers",vehicle.passengers)
        detailsHashMap.put("Cargo Capacity",vehicle.cargoCapacity)
        detailsHashMap.put("Consumables",vehicle.consumables)
        detailsHashMap.put("Vehicle Class",vehicle.vehicleClass)
        detailsHashMap.put("Edited",vehicle.edited)
        detailsHashMap.put("Created",vehicle.created)
        detailsHashMap.put("Url",vehicle.url)
       return detailsHashMap
    }

    override fun getItemCount()=vehicles.size
}
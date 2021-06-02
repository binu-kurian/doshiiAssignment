package au.com.doshii.starwars.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import au.com.doshii.starwars.R
import au.com.doshii.starwars.model.People
import au.com.doshii.starwars.view.fragment.ListFragmentDirections
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.android.synthetic.main.item_list.view.list_item_home
import java.util.ArrayList
import java.util.HashMap

class PeopleListAdapter(private var peoples: ArrayList<People>):
    RecyclerView.Adapter<PeopleListAdapter.PeopleViewHolder>() {
    class PeopleViewHolder(var view: View):RecyclerView.ViewHolder(view)

    fun updatePeople(newPeopleResult: ArrayList<People>) {
        peoples.clear()
        peoples.addAll(newPeopleResult)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.item_list,parent,false)
        return PeopleViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.view.listTitleText.text=peoples[position].name
        holder.view.list_item_home.setOnClickListener {
            val action = ListFragmentDirections.actionGotoDetailsFragment(getDetailsAsHaspMap(peoples[position]))

            Navigation.findNavController(holder.view).navigate(action)
        }
    }

    private fun getDetailsAsHaspMap(people: People): HashMap<Any, Any> {
        val detailsHashMap = HashMap<Any, Any>()
        detailsHashMap.put("Name",people.name)
        detailsHashMap.put("Birth Year",people.birth_year)
        detailsHashMap.put("Created",people.created)
        detailsHashMap.put("Edited",people.edited)
        detailsHashMap.put("Eye Color",people.eye_color)
        detailsHashMap.put("Hair Color",people.hair_color)
        detailsHashMap.put("Height",people.height)
        detailsHashMap.put("Home World",people.homeworld)
        detailsHashMap.put("Mass",people.mass)
        detailsHashMap.put("Skin Color",people.skin_color)
       return detailsHashMap
    }

    override fun getItemCount()=peoples.size
}
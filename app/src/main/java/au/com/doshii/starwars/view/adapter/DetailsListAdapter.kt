package au.com.doshii.starwars.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.com.doshii.starwars.R
import kotlinx.android.synthetic.main.item_details.view.*
import java.util.ArrayList
import java.util.HashMap

class DetailsListAdapter(private var detailsHashMap: HashMap<Any,Any>):
    RecyclerView.Adapter<DetailsListAdapter.PeopleViewHolder>() {

    private var keys = ArrayList<Any>()
    private var values = ArrayList<Any>()
    class PeopleViewHolder(var view: View):RecyclerView.ViewHolder(view)

    fun updateDetails(newDetailsHashMap: HashMap<Any,Any>) {
        detailsHashMap.clear()
        detailsHashMap.putAll(newDetailsHashMap)
        keys.clear()
        keys.addAll(detailsHashMap.keys)
        values.clear()
        values.addAll(detailsHashMap.values)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.item_details,parent,false)
        return PeopleViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.view.keyText.text=keys.get(position)?.toString()
        holder.view.valueText.text = values.get(position)?.toString()
    }

    override fun getItemCount()=keys.size
}
package au.com.doshii.starwars.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import au.com.doshii.starwars.R
import au.com.doshii.starwars.model.DataOption
import au.com.doshii.starwars.view.fragment.HomeFragmentDirections
import kotlinx.android.synthetic.main.item_home.view.*
import java.util.ArrayList

class HomeListAdapter(private val dataOptionList:ArrayList<DataOption>):
    RecyclerView.Adapter<HomeListAdapter.DataOptionViewHolder>() {

    class DataOptionViewHolder(var view: View):RecyclerView.ViewHolder(view)

    fun updateDataOptionsList(newDataOptionList:List<DataOption>) {
        dataOptionList.clear()
        dataOptionList.addAll(newDataOptionList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataOptionViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.item_home,parent,false)
        return DataOptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataOptionViewHolder, position: Int) {
        holder.view.dataOptionText.text=dataOptionList[position].name
        holder.view.list_item_home.setOnClickListener {
            val action = HomeFragmentDirections.actionGotoListFragment(dataOptionList.get(position))
            Navigation.findNavController(holder.view).navigate(action)
        }
    }

    override fun getItemCount()=dataOptionList.size
}
package au.com.doshii.starwars.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import au.com.doshii.starwars.R
import au.com.doshii.starwars.model.Film
import au.com.doshii.starwars.view.fragment.ListFragmentDirections
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.android.synthetic.main.item_list.view.list_item_home
import java.util.ArrayList
import java.util.HashMap

class FilmListAdapter(private var films: ArrayList<Film>):
    RecyclerView.Adapter<FilmListAdapter.FilmViewHolder>() {
    class FilmViewHolder(var view: View):RecyclerView.ViewHolder(view)

    fun updatefilm(newfilmResult: ArrayList<Film>) {
        films.clear()
        films.addAll(newfilmResult)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.item_list,parent,false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.view.listTitleText.text=films[position].title
        holder.view.list_item_home.setOnClickListener {
            val action = ListFragmentDirections.actionGotoDetailsFragment(getDetailsAsHaspMap(films[position]))

            Navigation.findNavController(holder.view).navigate(action)
        }
    }

    private fun getDetailsAsHaspMap(film: Film): HashMap<Any, Any> {
        val detailsHashMap = HashMap<Any, Any>()
        detailsHashMap.put("Title",film.title)
        detailsHashMap.put("Release Date",film.releaseDate)
        detailsHashMap.put("Created",film.created)
        detailsHashMap.put("Director",film.director)
        detailsHashMap.put("Episode ID",film.episodeID)
        detailsHashMap.put("Producer",film.producer)
        detailsHashMap.put("Edited",film.edited)
        detailsHashMap.put("url",film.url)
        detailsHashMap.put("Opening Crawl",film.openingCrawl)
       return detailsHashMap
    }

    override fun getItemCount()=films.size
}
package au.com.doshii.starwars.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.doshii.starwars.R
import au.com.doshii.starwars.model.*
import au.com.doshii.starwars.view.*
import au.com.doshii.starwars.view.adapter.*
import au.com.doshii.starwars.viewmodel.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_list.*


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    var dataOption: DataOption ?= null
    private lateinit var baseViewModel: BaseViewModel
    private val listAdapter = PeopleListAdapter(arrayListOf())
    private val filmAdapter = FilmListAdapter(arrayListOf())
    private val planetAdapter = PlanetListAdapter(arrayListOf())
    private val speciesAdapter = SpeciesListAdapter(arrayListOf())
    private val starShipAdapter = StarShipListAdapter(arrayListOf())
    private val vehicleAdapter = VechicleListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
    private val peopleDataObserver = Observer<PeopleResult> { list ->
        list?.let {
            dataList.visibility = View.VISIBLE
            listAdapter.updatePeople(it.peoples)
            list_no_data_text.visibility =  View.GONE
            list_loading.visibility = View.GONE
        }
    }
    private val speciesDataObserver = Observer<SpeciesResult> { list ->
        list?.let {
            dataList.visibility = View.VISIBLE
            speciesAdapter.updateSpecies(it.speciess)
            list_no_data_text.visibility =  View.GONE
            list_loading.visibility = View.GONE
        }
    }
    private val filmDataObserver = Observer<FilmResult> { list ->
        list?.let {
            dataList.visibility = View.VISIBLE
            filmAdapter.updatefilm(it.films)
            list_no_data_text.visibility =  View.GONE
            list_loading.visibility = View.GONE
        }
    }
    private val starShipDataObserver = Observer<StarShipResult> { list ->
        list?.let {
            dataList.visibility = View.VISIBLE
            starShipAdapter.updateStarShip(it.starShips)
            list_no_data_text.visibility =  View.GONE
            list_loading.visibility = View.GONE
        }
    }
    private val planetDataObserver = Observer<PlanetResult> { list ->
        list?.let {
            dataList.visibility = View.VISIBLE
            planetAdapter.updatePlanet(it.planets)
            list_no_data_text.visibility =  View.GONE
            list_loading.visibility = View.GONE
        }
    }
    private val vehicleDataObserver = Observer<VehiclesResult> { list ->
        list?.let {
            dataList.visibility = View.VISIBLE
            vehicleAdapter.updateVehicle(it.vehicles)
            list_no_data_text.visibility =  View.GONE
            list_loading.visibility = View.GONE
        }
    }
    private val dataLoadingObserver = Observer<Boolean> { isLoading ->
        list_loading.visibility  = if (isLoading)View.VISIBLE else View.GONE
        if (isLoading) {
            dataList.visibility =  View.GONE
            list_no_data_text.visibility = View.GONE
        }
    }

    private val noDataObserver = Observer<Boolean> { isError ->
        list_no_data_text.visibility  = if (isError)View.VISIBLE else View.GONE
        if (isError) {
            dataList.visibility =  View.GONE
            list_loading.visibility = View.GONE
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
        refreshList.setOnRefreshListener {
            loadData()
            refreshList.setRefreshing(false);
        }
    }

    private fun loadData() {
        arguments?.let {
            dataOption = ListFragmentArgs.fromBundle(it).dataOption
            (activity as AppCompatActivity?)!!.supportActionBar!!.title = dataOption?.name
            if (dataOption?.name.equals("People")) {
                baseViewModel = ViewModelProviders.of(this).get(PeopleViewModel::class.java)
                (baseViewModel as PeopleViewModel).peopleResult.observe(this, peopleDataObserver)
            } else  if (dataOption?.name.equals("Films")) {
                baseViewModel = ViewModelProviders.of(this).get(FilmViewModel::class.java)
                (baseViewModel as FilmViewModel).filmResult.observe(this, filmDataObserver)
            }
            else  if (dataOption?.name.equals("Planets")) {
                baseViewModel = ViewModelProviders.of(this).get(PlanetViewModel::class.java)
                (baseViewModel as PlanetViewModel).planetResult.observe(this, planetDataObserver)
            }else  if (dataOption?.name.equals("Species")) {
                baseViewModel = ViewModelProviders.of(this).get(SpeciesViewModel::class.java)
                (baseViewModel as SpeciesViewModel).speciesResult.observe(this, speciesDataObserver)
            }
            else  if (dataOption?.name.equals("Starships")) {
                baseViewModel = ViewModelProviders.of(this).get(StarShipViewModel::class.java)
                (baseViewModel as StarShipViewModel).speciesResult.observe(this, starShipDataObserver)
            }else  if (dataOption?.name.equals("Vehicles")) {
                baseViewModel = ViewModelProviders.of(this).get(VehicleViewModel::class.java)
                (baseViewModel as VehicleViewModel).vehiclesResult.observe(this, vehicleDataObserver)
            }
            baseViewModel.loading.observe(this, dataLoadingObserver)
            baseViewModel.onNoData.observe(this, noDataObserver)
            baseViewModel.url = dataOption?.url
            baseViewModel.refresh()

            dataList.apply {
                layoutManager = LinearLayoutManager(context)
                if (dataOption?.name.equals("People")) {
                    adapter = listAdapter
                }else if (dataOption?.name.equals("Films")) {
                    adapter = filmAdapter
                }else if (dataOption?.name.equals("Planets")) {
                    adapter = planetAdapter
                }else if (dataOption?.name.equals("Species")) {
                    adapter = speciesAdapter
                }else if (dataOption?.name.equals("Starships")) {
                    adapter = starShipAdapter
                }else if (dataOption?.name.equals("Vehicles")) {
                    adapter = vehicleAdapter
                }
            }
        }
    }
}
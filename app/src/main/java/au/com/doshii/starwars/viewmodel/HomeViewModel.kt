package au.com.doshii.starwars.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import au.com.doshii.starwars.model.BaseApi
import au.com.doshii.starwars.model.DataOption

class HomeViewModel(application: Application):BaseViewModel(application) {

    val dataOptions by lazy { MutableLiveData<List<DataOption>>() }

    override fun refresh() {
        getData()
    }

    override fun inject() {
        TODO("Not yet implemented")
    }

    override fun getData() {
        val dataOption1 = DataOption("People",BaseApi.PEOPLE_URI)
        val dataOption2 = DataOption("Films",BaseApi.FILM_URI)
        val dataOption3 = DataOption("Planets",BaseApi.PLANET_URI)
        val dataOption4 = DataOption("Species",BaseApi.SPECIES_URI)
        val dataOption5 = DataOption("Starships",BaseApi.STARSHIP_URI)
        val dataOption6 = DataOption("Vehicles",BaseApi.VEHICLE_URI)
        val dataOptionList = arrayListOf(dataOption1,dataOption2,dataOption3,dataOption4,dataOption5,dataOption6)
        dataOptions.value = dataOptionList
        onNoData.value = false
        loading.value = false
    }

}
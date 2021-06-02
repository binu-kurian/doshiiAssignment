package au.com.doshii.starwars

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import au.com.doshii.starwars.model.BaseApi
import au.com.doshii.starwars.model.DataOption
import au.com.doshii.starwars.viewmodel.HomeViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HomeViewModelTest  {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    val application = Mockito.mock(Application::class.java)
    private var dataOptionModel = HomeViewModel(application)

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }
    @Test
    fun verifyOptionsAvailableForHome() {
        dataOptionModel.refresh()
        Assert.assertEquals(6,dataOptionModel.dataOptions.value?.size)
        Assert.assertEquals(false,dataOptionModel.onNoData.value)
        Assert.assertEquals(DataOption("People",BaseApi.PEOPLE_URI),dataOptionModel.dataOptions.value?.get(0))
        Assert.assertEquals(DataOption("Films",BaseApi.FILM_URI),dataOptionModel.dataOptions.value?.get(1))
        Assert.assertEquals(DataOption("Planets",BaseApi.PLANET_URI),dataOptionModel.dataOptions.value?.get(2))
        Assert.assertEquals(DataOption("Species",BaseApi.SPECIES_URI),dataOptionModel.dataOptions.value?.get(3))
        Assert.assertEquals(DataOption("Starships",BaseApi.STARSHIP_URI),dataOptionModel.dataOptions.value?.get(4))
        Assert.assertEquals(DataOption("Vehicles",BaseApi.VEHICLE_URI),dataOptionModel.dataOptions.value?.get(5))


    }
}


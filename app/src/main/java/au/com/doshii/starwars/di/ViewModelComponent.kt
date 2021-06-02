package au.com.doshii.starwars.di

import au.com.doshii.starwars.viewmodel.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class,AppModule::class])
interface ViewModelComponent {
    fun inject(peopleViewModel: PeopleViewModel)
    fun inject(filmViewModel: FilmViewModel)
    fun inject(planetViewModel: PlanetViewModel)
    fun inject(speciesViewModel: SpeciesViewModel)
    fun inject(starShipViewModel: StarShipViewModel)
    fun inject(vehicleViewModel:VehicleViewModel)
}
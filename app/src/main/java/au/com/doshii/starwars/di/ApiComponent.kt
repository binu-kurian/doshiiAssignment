package au.com.doshii.starwars.di

import androidx.lifecycle.AndroidViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class])
@Singleton
interface ApiComponent {

}
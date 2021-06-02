package au.com.doshii.starwars.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class BaseApi constructor(context: Context) {


        companion object {
            @Volatile
            private var INSTANCE: BaseApi? = null
            fun getInstance(context: Context) =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: BaseApi(context).also {
                        INSTANCE = it
                    }
                }

            var PEOPLE_URI = "https://swapi.dev/api/people/"
            var FILM_URI = "https://swapi.dev/api/films/"
            var PLANET_URI = "https://swapi.dev/api/planets/"
            var SPECIES_URI = "https://swapi.dev/api/species/"
            var STARSHIP_URI = "https://swapi.dev/api/starships/"
            var VEHICLE_URI = "https://swapi.dev/api/vehicles/"
        }

        val requestQueue: RequestQueue by lazy {
            Volley.newRequestQueue(context.applicationContext)
        }
        fun <T> addToRequestQueue(req: Request<T>) {
            requestQueue.add(req)
        }
    }

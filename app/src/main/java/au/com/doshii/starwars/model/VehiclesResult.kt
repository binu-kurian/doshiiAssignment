package au.com.doshii.starwars.model

import com.google.gson.annotations.SerializedName

data class VehiclesResult (
    val count: Long,
    val next: String,
    val previous: Any? = null,
    @SerializedName("results")
    val vehicles: ArrayList<Vehicle>
)

data class Vehicle(
    val name: String,
    val model: String,
    val manufacturer: String,
    val costInCredits: String,
    val length: String,
    val maxAtmospheringSpeed: String,
    val crew: String,
    val passengers: String,
    val cargoCapacity: String,
    val consumables: String,
    val vehicleClass: String,
    val pilots: List<String>,
    val films: List<String>,
    val created: String,
    val edited: String,
    val url: String)

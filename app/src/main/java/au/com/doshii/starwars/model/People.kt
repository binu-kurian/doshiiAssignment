package au.com.doshii.starwars.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PeopleResult (
    val count: Long,
    val next: String,
    val previous: Any? = null,
    @SerializedName("results")
    val peoples: ArrayList<People>
)

data class People(val name: String,
                  val height: String,
                  val mass: String,
                  val hair_color: String,
                  val skin_color: String,
                  val eye_color: String,
                  val birth_year: String,
                  val gender: Gender,
                  val homeworld: String,
                  val films: List<String>,
                  val species: List<String>,
                  val vehicles: List<String>,
                  val starships: List<String>,
                  val created: String,
                  val edited: String,
                  val url: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,

        TODO("gender"),
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.createStringArrayList()!!,
        parcel.createStringArrayList()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<People> {
        override fun createFromParcel(parcel: Parcel): People {
            return People(parcel)
        }

        override fun newArray(size: Int): Array<People?> {
            return arrayOfNulls(size)
        }
    }
}

enum class Gender(val value: String) {
    Female("female"),
    Male("male"),
    NA("n/a");

    companion object {
        public fun fromValue(value: String): Gender = when (value) {
            "female" -> Female
            "male"   -> Male
            "n/a"    -> NA
            else     -> throw IllegalArgumentException()
        }
    }
}
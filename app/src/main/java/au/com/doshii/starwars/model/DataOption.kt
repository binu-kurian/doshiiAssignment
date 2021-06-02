package au.com.doshii.starwars.model

import android.os.Parcel
import android.os.Parcelable

data class DataOption(val name:String,val url:String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun describeContents()=0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeString(name)
        dest!!.writeString(url)
    }

    companion object CREATOR : Parcelable.Creator<DataOption> {
        override fun createFromParcel(parcel: Parcel): DataOption {
            return DataOption(parcel)
        }

        override fun newArray(size: Int): Array<DataOption?> {
            return arrayOfNulls(size)
        }
    }
}

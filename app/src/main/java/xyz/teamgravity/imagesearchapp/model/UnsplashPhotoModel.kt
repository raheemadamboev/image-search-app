package xyz.teamgravity.imagesearchapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashPhotoModel(
    val id: String,
    val description: String?,
    val urls: UrlModel,
    val user: UserModel
) : Parcelable {

    @Parcelize
    data class UrlModel(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String
    ) : Parcelable

    @Parcelize
    data class UserModel(
        val name: String,
        val username: String
    ) : Parcelable {

        // required for API to send analytics
        val attributionUrl get() = "https://unsplash.com/$username?utm_source=ImageSearchApp&utm_medium=referral"
    }
}
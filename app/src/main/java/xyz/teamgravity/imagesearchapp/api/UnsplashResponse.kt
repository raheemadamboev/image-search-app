package xyz.teamgravity.imagesearchapp.api

import xyz.teamgravity.imagesearchapp.model.UnsplashPhotoModel

data class UnsplashResponse(
    val results: List<UnsplashPhotoModel>
)
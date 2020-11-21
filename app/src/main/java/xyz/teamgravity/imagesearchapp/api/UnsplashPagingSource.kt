package xyz.teamgravity.imagesearchapp.api

import androidx.paging.PagingSource
import retrofit2.HttpException
import xyz.teamgravity.imagesearchapp.model.UnsplashPhotoModel
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class UnsplashPagingSource(
    private val unsplashAPI: UnsplashAPI,
    private val query: String
) : PagingSource<Int, UnsplashPhotoModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhotoModel> {
        // it returns null if it is the first page
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX

        return try {
            val response = unsplashAPI.searchPhotos(query, position, params.loadSize)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}
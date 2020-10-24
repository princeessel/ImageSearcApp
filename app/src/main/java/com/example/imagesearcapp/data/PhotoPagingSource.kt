package com.example.imagesearcapp.data

import androidx.paging.PagingSource
import com.example.imagesearcapp.api.PhotoApi
import java.io.IOException

private const val PHOTO_STARTING_PAGE_INDEX = 1

class PhotoPagingSource(
    private val photoApi: PhotoApi,
    private val query: String

) : PagingSource<Int, Photo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: PHOTO_STARTING_PAGE_INDEX

        return try {
            val response = photoApi.searchPhotos(query, position, params.loadSize)
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == PHOTO_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }
}
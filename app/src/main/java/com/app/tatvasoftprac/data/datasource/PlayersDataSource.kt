package com.app.tatvasoftprac.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.tatvasoftprac.ktx.Logger
import com.app.tatvasoftprac.model.UserResponse
import com.app.tatvasoftprac.network.APIConstant.STARTING_PAGE_INDEX
import com.app.tatvasoftprac.network.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UsersDataSource @Inject constructor(private val apiService: ApiService) :
    PagingSource<Int, UserResponse.UserModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponse.UserModel> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {

            val response = apiService.getUserList(page)

            val users = response.body()
            val totalPage = response.body()?.totalPages ?: 0
            Logger.d("total pages : ${response.body()?.totalPages ?: 0} current page : $page")

            LoadResult.Page(
                data = users?.data ?: listOf(),
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page > totalPage) null else page + 1
            )
        } catch (exception: IOException) {
            val error = IOException("Please check internet connection...")
            LoadResult.Error(error)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserResponse.UserModel>): Int? {
        return state.anchorPosition
    }
}
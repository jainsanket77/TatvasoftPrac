package com.app.tatvasoftprac.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.app.tatvasoftprac.data.datasource.UsersDataSource
import com.app.tatvasoftprac.model.UserResponse
import com.app.tatvasoftprac.network.ApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val service: ApiService) {

    suspend fun getUserList(offset: Int = 1): Response<UserResponse> {
        return service.getUserList(offset)
    }

    fun getUsersList(): LiveData<PagingData<UserResponse.UserModel>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 25),
            pagingSourceFactory = {
                UsersDataSource(service)
            }
        ).liveData
    }
}
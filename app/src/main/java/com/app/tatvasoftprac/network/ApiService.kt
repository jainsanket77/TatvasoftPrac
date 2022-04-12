package com.app.tatvasoftprac.network

import com.app.tatvasoftprac.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(APIConstant.API_USERS)
    suspend fun getUserList(
        @Query(APIConstant.PARAM_PAGE) page: Int,
    ): Response<UserResponse>
}
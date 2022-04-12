package com.app.tatvasoftprac.ui.fragment.task2

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.tatvasoftprac.base.BaseViewModel
import com.app.tatvasoftprac.model.UserResponse
import com.app.tatvasoftprac.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Task2ViewModel @Inject constructor(
    private val repository: UserRepository
) : BaseViewModel() {

    var pagingUserResponseObservable: LiveData<PagingData<UserResponse.UserModel>>? = null
    fun getUserList(): LiveData<PagingData<UserResponse.UserModel>> {
        val newResultLiveData: LiveData<PagingData<UserResponse.UserModel>> =
            repository.getUsersList().cachedIn(viewModelScope)
        pagingUserResponseObservable = newResultLiveData
        return newResultLiveData
    }
}
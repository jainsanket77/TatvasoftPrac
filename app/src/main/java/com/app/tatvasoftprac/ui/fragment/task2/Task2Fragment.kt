package com.app.tatvasoftprac.ui.fragment.task2

import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import com.app.tatvasoftprac.BR
import com.app.tatvasoftprac.R
import com.app.tatvasoftprac.base.BaseFragment
import com.app.tatvasoftprac.databinding.FragmentTask2Binding
import com.app.tatvasoftprac.ktx.Logger
import com.app.tatvasoftprac.ktx.gone
import com.app.tatvasoftprac.ktx.isNetworkConnected
import com.app.tatvasoftprac.ktx.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Task2Fragment : BaseFragment<FragmentTask2Binding>() {

    override val layoutId = R.layout.fragment_task2
    override val bindingVariable = BR.task2ViewModel

    private val viewModel: Task2ViewModel by navGraphViewModels(R.id.main_graph) {
        defaultViewModelProviderFactory
    }

    private var userJob: Job? = null
    private val adapter = UserPagingAdapter()

    override fun init() {
        setUpAdapter()
        setUpObserver()
    }

    @OptIn(ExperimentalPagingApi::class)
    fun setUpObserver() {
        try {
            userJob?.cancel()
            userJob = lifecycleScope.launch {
                viewModel.getUserList().observe(viewLifecycleOwner) {
                    if (requireActivity().isNetworkConnected()) {
                        binding.progressCircular.visible()
                        adapter.submitData(lifecycle, it)
                    } else {
                        showSnackbar("Please check internet connection...")
                    }
                }
            }
        } catch (e: Exception) {
            Logger.e(e)
        }
    }

    private fun setUpAdapter() {
        try {
            binding.rvUser.adapter = adapter
            adapter.addLoadStateListener { loadState ->
                if (loadState.refresh is LoadState.Loading) {
                    binding.progressCircular.visible()
                } else {
                    binding.progressCircular.gone()
                    val error = when {
                        loadState.mediator?.prepend is LoadState.Error -> loadState.mediator?.prepend as LoadState.Error
                        loadState.mediator?.append is LoadState.Error -> loadState.mediator?.append as LoadState.Error
                        loadState.mediator?.refresh is LoadState.Error -> loadState.mediator?.refresh as LoadState.Error
                        else -> null
                    }
                    error?.let {
                        Logger.e(" Pagination error = ${it.error}")
                    }
                }
            }
        } catch (e: Exception) {
            Logger.e(e)
        }
    }
}
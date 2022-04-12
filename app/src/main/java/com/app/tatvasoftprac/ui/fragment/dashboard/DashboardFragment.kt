package com.app.tatvasoftprac.ui.fragment.dashboard

import androidx.navigation.navGraphViewModels
import com.app.tatvasoftprac.BR
import com.app.tatvasoftprac.R
import com.app.tatvasoftprac.base.BaseFragment
import com.app.tatvasoftprac.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    override val layoutId = R.layout.fragment_dashboard
    override val bindingVariable = BR.dashboardViewModel

    private val viewModel: DashboardViewModel by navGraphViewModels(R.id.main_graph)

    override fun init() {
        try {
            binding.apply {
                dashboardViewModel = viewModel
                btnTask1.setOnClickListener {
                    navigate(R.id.action_dashboardFragment_to_task1Fragment)
                }
                btnTask2.setOnClickListener {

                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
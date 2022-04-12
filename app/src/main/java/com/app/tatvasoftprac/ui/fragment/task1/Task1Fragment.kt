package com.app.tatvasoftprac.ui.fragment.task1

import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.app.tatvasoftprac.BR
import com.app.tatvasoftprac.R
import com.app.tatvasoftprac.base.BaseFragment
import com.app.tatvasoftprac.databinding.FragmentTask1Binding
import com.app.tatvasoftprac.ktx.Logger
import com.app.tatvasoftprac.ktx.clearValue
import com.app.tatvasoftprac.ktx.hideKeyboard
import com.app.tatvasoftprac.model.BLOCK
import com.app.tatvasoftprac.model.BlockModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.sqrt

@AndroidEntryPoint
class Task1Fragment : BaseFragment<FragmentTask1Binding>() {

    override val layoutId = R.layout.fragment_task1
    override val bindingVariable = BR.task1ViewModel

    private val viewModel: Task1ViewModel by navGraphViewModels(R.id.main_graph)

    lateinit var adapter: Task1Adapter

    override fun init() {
        binding.apply {
            task1ViewModel = viewModel

            btnSubmit.setOnClickListener {
                try {
                    val value = viewModel.number.get()?:""
                    if (value.isNotEmpty()) {
                        btnSubmit.hideKeyboard()
                        etCount.clearValue()

                        val number = value.toInt()
                        val isSqrt = isPerfectSquare(number) // check number is square root
                        if (isSqrt) {
                            val span = sqrt(value.toDouble()).toInt() // calculate span
                            rvBlock.layoutManager = GridLayoutManager(requireContext(), span)

                            fillBlockRv(number)
                        } else {
                            showSnackbar("Please enter square root number")
                        }
                    }
                } catch (e: Exception) {
                    Logger.e(e)
                }
            }
        }
    }

    private fun fillBlockRv(number: Int) {
        // create list with grey block.
        val list = MutableList(number) {
            BlockModel(BLOCK.GREY)
        }
        adapter = Task1Adapter(list, object : Task1Selection {
            override fun onItemClick(pos: Int, block: BlockModel) {
                adapter.makeAvailable()
            }

            override fun onBlockFinish() {
                showSnackbar("Wow!, You did it!")
                binding.root.hideKeyboard()
            }
        })
        binding.rvBlock.adapter = adapter
        // after 750MS random block is clickable.
        delay(750L) {
            adapter.makeAvailable()
        }
    }
}
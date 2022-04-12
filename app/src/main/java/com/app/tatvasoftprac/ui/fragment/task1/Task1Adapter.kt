package com.app.tatvasoftprac.ui.fragment.task1

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.tatvasoftprac.R
import com.app.tatvasoftprac.base.BaseViewHolder
import com.app.tatvasoftprac.databinding.ItemBlockBinding
import com.app.tatvasoftprac.model.BLOCK
import com.app.tatvasoftprac.model.BlockModel

class Task1Adapter(
    val blocks: MutableList<BlockModel>,
    val task1Selection: Task1Selection
) : RecyclerView.Adapter<Task1Adapter.BlockViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BlockViewHolder(
        ItemBlockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: BlockViewHolder, position: Int) = holder.onBind(position)

    override fun getItemCount() = blocks.size

    fun addBlocks(blocks: MutableList<BlockModel>) {
        this.blocks.addAll(blocks)
        notifyDataSetChanged()
    }

    inner class BlockViewHolder(binding: ItemBlockBinding) : BaseViewHolder(binding.root) {

        private val mBinding: ItemBlockBinding = binding

        override fun onBind(position: Int) {
            val block = blocks[position]
            mBinding.apply {
                val context = root.context
                aivBlock.setBackgroundColor(
                    when (block.block) {
                        BLOCK.RED -> getColor(context, R.color.red)
                        BLOCK.BLUE -> getColor(context, R.color.blue)
                        else -> getColor(context, R.color.black_20)
                    }
                )

                aivBlock.setOnClickListener {
                    if (block.block == BLOCK.RED) {
                        task1Selection.onItemClick(position, block)
                        block.block = BLOCK.BLUE

                        notifyItemChanged(position)
                        val checkBlock = blocks.filter { it.block == BLOCK.BLUE }
                        if (checkBlock.size == blocks.size) {
                            task1Selection.onBlockFinish()
                        }
                    }
                }
            }
        }
    }

    fun makeAvailable() {
        try {
            val availableBlock = blocks.filter { it.block == BLOCK.GREY }
            availableBlock.random().block = BLOCK.RED
            notifyDataSetChanged()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getColor(context: Context, idRes: Int): Int {
        return ContextCompat.getColor(context, idRes)
    }
}
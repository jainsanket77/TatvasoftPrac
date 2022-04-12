package com.app.tatvasoftprac.ui.fragment.task1

import com.app.tatvasoftprac.model.BlockModel

interface Task1Selection {
    fun onItemClick(pos: Int, block: BlockModel)

    fun onBlockFinish()
}
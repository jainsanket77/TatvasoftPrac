package com.app.tatvasoftprac.model

data class BlockModel(
    var block: BLOCK = BLOCK.GREY,
)

enum class BLOCK {
    BLUE,
    RED,
    GREY,
}
package com.reham11203.flashfeed.common

data class ErrorState(
    val errorMessage: String? = null,
    val onTryAgain: (() -> Unit)? = null
)
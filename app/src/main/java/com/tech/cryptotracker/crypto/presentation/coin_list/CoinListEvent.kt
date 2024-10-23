package com.tech.cryptotracker.crypto.presentation.coin_list

import com.tech.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error : NetworkError) : CoinListEvent
}
package com.tech.cryptotracker.crypto.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.tech.cryptotracker.crypto.data.networking.dto.CoinDto
import com.tech.cryptotracker.crypto.data.networking.dto.CoinPriceDto
import com.tech.cryptotracker.crypto.domain.Coin
import com.tech.cryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsed = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant.ofEpochSecond(time).atZone(ZoneId.of("UTC"))
    )
}
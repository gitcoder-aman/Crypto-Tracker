package com.tech.cryptotracker.crypto.presentation.models

import androidx.annotation.DrawableRes
import com.tech.cryptotracker.crypto.domain.Coin
import com.tech.cryptotracker.core.presentation.util.getDrawableIdForCoin
import com.tech.cryptotracker.crypto.presentation.coin_detail.DataPoint
import java.text.NumberFormat
import java.util.Locale

data class CoinUi(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsed: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    val coinPriceHistory : List<DataPoint> = emptyList(),
    @DrawableRes val iconRes: Int
)
data class DisplayableNumber(
    val value : Double,
    val formatted : String
)
fun Coin.toCoinUi() : CoinUi{
    return CoinUi(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsed = marketCapUsed.toDisplayableNumber(),
        priceUsd = priceUsd.toDisplayableNumber(),
        changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol)
    )
}
fun Double.toDisplayableNumber() : DisplayableNumber{
   val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
       maximumFractionDigits = 2
       minimumFractionDigits = 2
   }
    return  DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}
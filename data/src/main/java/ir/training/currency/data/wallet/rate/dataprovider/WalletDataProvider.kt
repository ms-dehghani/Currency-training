package ir.training.currency.data.wallet.rate.dataprovider

import ir.training.currency.domain.model.wallet.WalletItem

interface WalletDataProvider {
    suspend fun getWallet(): WalletItem
}
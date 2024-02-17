package ir.training.currency.data.wallet.rate.repository

import ir.training.currency.data.wallet.rate.dataprovider.WalletDataProvider
import ir.training.currency.domain.model.wallet.WalletItem
import ir.training.currency.domain.repository.wallet.WalletRepository
import javax.inject.Inject

internal class WalletRepositoryImpl @Inject constructor(private val dataProvider: WalletDataProvider) :
    WalletRepository {

    override suspend fun getWallet(): WalletItem {
        return dataProvider.getWallet()
    }

}
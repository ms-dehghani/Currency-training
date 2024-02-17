package ir.training.currency.data.wallet.rate.dataprovider

import ir.training.currency.data.ServiceRepository
import ir.training.currency.domain.model.wallet.WalletItem
import javax.inject.Inject


internal class WalletDataProviderImpl @Inject constructor(private val repository: ServiceRepository) :
    WalletDataProvider {

    override suspend fun getWallet(): WalletItem {
        return repository.getWallet()
    }

}
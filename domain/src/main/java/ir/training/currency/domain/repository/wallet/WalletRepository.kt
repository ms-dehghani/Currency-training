package ir.training.currency.domain.repository.wallet

import ir.training.currency.domain.model.wallet.WalletItem

interface WalletRepository {
    suspend fun getWallet(): WalletItem
}
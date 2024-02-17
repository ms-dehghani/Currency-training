package ir.training.currency.domain.usecase.wallet

import ir.training.currency.domain.model.wallet.WalletItem
import ir.training.currency.domain.repository.wallet.WalletRepository

class WalletUseCase(private val repository: WalletRepository) {
    suspend operator fun invoke(): WalletItem {
        return repository.getWallet()
    }
}
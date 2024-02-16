package ir.training.currency.domain.model.wallet

data class WalletItem(private var currencyList: MutableList<WalletCurrency>) {

    fun getCurrencyList(): List<WalletCurrency> {
        return currencyList
    }

    fun addCurrency(currency: WalletCurrency) {
        currencyList.add(currency)
    }

}
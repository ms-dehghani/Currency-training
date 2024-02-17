package ir.training.currency.domain.model.wallet

data class WalletItem(private var currencyList: MutableList<WalletCurrency>) {

    fun getCurrencyList(): List<WalletCurrency> {
        return currencyList
    }

    fun addCurrency(currency: WalletCurrency) {
        if (contains(currency)) {
            currencyList.forEach {
                if (it.name == currency.name) {
                    it.amount += currency.amount
                }
            }
        } else {
            currencyList.add(currency)
        }
    }

    private fun contains(currency: WalletCurrency): Boolean {
        return currencyList.find { it.name == currency.name } != null
    }

}
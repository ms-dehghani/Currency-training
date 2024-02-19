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

    fun toStringValue():String{
        var result = "";
         currencyList.forEach {
             result+= "$ ${it.amount} ${it.name}" + "\n"
         }
        return result;
    }

    fun getEuroAmount():String{
        return "$ ${currencyList[0].amount} ${currencyList[0].name}"
    }

    private fun contains(currency: WalletCurrency): Boolean {
        return currencyList.find { it.name == currency.name } != null
    }

}
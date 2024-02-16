package ir.training.marvelcomics.data.comic.item.repository

import ir.training.marvelcomics.data.comic.item.dataprovider.ComicItemDataProvider
import ir.training.marvelcomics.domain.model.CurrencyRateItem
import ir.training.marvelcomics.domain.repository.Currency.item.CurrencyRateRepository
import javax.inject.Inject

internal class ComicItemRepositoryImpl @Inject constructor(private val dataProvider: ComicItemDataProvider) :
    CurrencyRateRepository {

    override suspend fun getComicById(id: Int):CurrencyRateItem? {
        return dataProvider.getComicItemByID(id)
    }

}
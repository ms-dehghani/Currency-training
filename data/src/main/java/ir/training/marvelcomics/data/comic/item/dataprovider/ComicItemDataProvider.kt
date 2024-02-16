package ir.training.marvelcomics.data.comic.item.dataprovider

import ir.training.marvelcomics.domain.model.CurrencyRateItem

interface ComicItemDataProvider {
    suspend fun getComicItemByID(comicID: Int): CurrencyRateItem?
}
package ir.training.marvelcomics.data.comic.list.repository

import androidx.paging.PagingData
import ir.training.marvelcomics.data.comic.list.dataprovider.ComicListDataProvider
import ir.training.marvelcomics.domain.model.CurrencyRateItem
import ir.training.marvelcomics.domain.repository.Currency.list.ComicListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ComicListRepositoryImpl @Inject constructor(private val dataProvider: ComicListDataProvider) :
    ComicListRepository {

    suspend override fun getComicList(): Flow<PagingData<CurrencyRateItem>> {
        return dataProvider.getComicList()
    }

}
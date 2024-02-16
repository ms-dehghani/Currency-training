package ir.training.marvelcomics.data.comic.list.dataprovider

import androidx.paging.PagingData
import ir.training.marvelcomics.domain.model.CurrencyRateItem
import kotlinx.coroutines.flow.Flow

interface ComicListDataProvider {
    suspend fun getComicList(): Flow<PagingData<CurrencyRateItem>>
}
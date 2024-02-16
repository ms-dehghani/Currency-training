package ir.training.marvelcomics.data

import androidx.paging.PagingData
import ir.training.marvelcomics.domain.model.CurrencyRateItem
import kotlinx.coroutines.flow.Flow

interface ServiceRepository {

    suspend fun getComicById(id: Int): CurrencyRateItem?

    suspend fun getComicList(): Flow<PagingData<CurrencyRateItem>>

}
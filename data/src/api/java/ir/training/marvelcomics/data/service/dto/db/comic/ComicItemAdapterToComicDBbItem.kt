package ir.training.marvelcomics.data.service.dto.db.comic

import ir.training.marvelcomics.domain.model.CurrencyRateItem

class ComicItemAdapterToComicDBbItem {

    fun map(comicResponse: CurrencyRateItem): ComicDbItem {
        return ComicDbItem(
            comicID = comicResponse.id,
            title = comicResponse.title,
            description = comicResponse.description,
            coverUrlPath = comicResponse.coverUrlPath,
            coverUrlExtension = comicResponse.coverUrlExtension,
            publishedDate = comicResponse.publishedDate,
            penciler = comicResponse.penciler,
            writer = comicResponse.writer,
        )
    }

}
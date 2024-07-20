package co.assignment.data.di

import co.assignment.data.api.BookSearchApi
import co.assignment.data.repository.BookRepositoryImpl
import co.assignment.database.FavoriteBookInfoDao
import co.assignment.domain.repository.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    internal fun provideBookRepository(
        bookSearchApi: BookSearchApi,
        favoriteBookInfoDao: FavoriteBookInfoDao
    ): BookRepository = BookRepositoryImpl(
        bookSearchApi = bookSearchApi,
        favoriteBookInfoDao = favoriteBookInfoDao,
    )

}
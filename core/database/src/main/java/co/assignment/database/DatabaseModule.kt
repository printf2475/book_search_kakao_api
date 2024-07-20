package co.assignment.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideBookDatabase(
        @ApplicationContext context: Context
    ): BookDatabase = BookDatabase.getDatabase(context)


    @Singleton
    @Provides
    fun provideFavoriteBookInfoDao(
        database: BookDatabase
    ): FavoriteBookInfoDao = database.favoriteBookInfoDao
}
package co.assignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import co.assignment.model.BookInfo

@Database(
    entities = [BookInfo::class],
    version = 1
)
@TypeConverters(
    DataListConverter::class
)
abstract class BookDatabase : RoomDatabase() {
    abstract val favoriteBookInfoDao: FavoriteBookInfoDao

    companion object {
        @Volatile
        private var database: BookDatabase? = null

        fun getDatabase(context: Context): BookDatabase {
            return database ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    "book_database"
                ).build()
                database = instance
                instance
            }
        }
    }
}
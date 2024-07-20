package co.assignment.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

/**
 * Serializable 추가시 KSP 및 RoomCompiler 에러 발생
 */
@Entity(tableName = "bookInfo")
data class BookInfo(
    @PrimaryKey val isbn: String,
    val title: String,
    val authors: List<String>,
    val translators: List<String>,
    val publisher: String,
    val datetime: String,
    val salePrice: Int,
    val price: Int,
    val thumbnail: String,
    val status: String,
    val contents: String,
    val url: String,
    val isFavorite: Boolean
)

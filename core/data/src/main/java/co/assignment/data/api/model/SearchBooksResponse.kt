package co.assignment.data.api.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class SearchBooksResponse(
    val meta: Meta,
    val documents: List<Document>
)

@Serializable
data class Meta(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("pageable_count")
    val pageableCount: Int,
    @SerializedName("is_end")
    val isEnd: Boolean
)

@Serializable
data class Document(
    val isbn: String,
    val title: String,
    val authors: List<String>,
    val translators: List<String>,
    val publisher: String,
    val datetime: String,
    @SerializedName("sale_price")
    val salePrice: Int,
    val price: Int,
    val thumbnail: String,
    val status: String,
    val contents: String,
    val url: String
)

package co.assignment.data.api

import co.assignment.data.api.model.SearchBooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface BookSearchApi {
    @GET("v3/search/book")
    suspend fun searchBooks(
        @Query("query") title: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): SearchBooksResponse
}
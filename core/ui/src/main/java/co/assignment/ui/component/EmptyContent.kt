package co.assignment.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun EmptyContent(type: EmptyContentType) {
    val title = when (type) {
        EmptyContentType.Search -> "검색 결과가 없어요"
        EmptyContentType.Favorite -> "즐겨찾기가 없어요"
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

enum class EmptyContentType {
    Search, Favorite
}
package co.assignment.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun BookInfoContent(
    modifier: Modifier = Modifier,
    title: String,
    authors: List<String>,
    salePrice: Int,
    price: Int,
    thumbnail: String,
    contents: String,
    isFavorite: Boolean,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {

    val authorText =
        "지은이 ${authors.firstOrNull() ?: ""}" + (("외 ${authors.size - 1}명").takeIf { authors.size - 1 > 0 }
            ?: "")
    Card(
        modifier = modifier.height(250.dp),
        onClick = onClick
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier
                    .padding(20.dp)
                    .size(30.dp)
                    .clickable(onClick = onFavoriteClick)
                    .align(Alignment.TopEnd),
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = if (isFavorite) Color.Red else Color.White
            )
            Row(modifier = Modifier.fillMaxHeight()) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.4f),
                    model = thumbnail,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                VerticalDivider()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                ) {
                    Text(
                        modifier = Modifier.padding(end = 40.dp),
                        text = "제목 : $title",
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    if (authors.isNotEmpty()) {
                        Text(text = authorText)
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    Text(
                        text = "내용 : $contents",
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = price.toString(),
                        fontSize = 12.sp,
                        textDecoration = TextDecoration.LineThrough
                    )

                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = "$salePrice 원"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewBookInfoContent() {
    BookInfoContent(
        modifier = Modifier.fillMaxWidth(),
        title = "test",
        authors = listOf("홍길동"),
        salePrice = 10000,
        price = 12000,
        thumbnail = "",
        contents = "test content\ndsaddsa\ndksaldkalsdk",
        isFavorite = false,
        onClick = { /*TODO*/ }) {
    }
}
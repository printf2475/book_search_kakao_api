package co.assignment.detail.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.assignment.detail.model.DetailActions
import co.assignment.model.BookInfo
import coil.compose.AsyncImage

@Composable
fun BookDetailScreen(
    bookInfo: BookInfo?,
    onActions: (DetailActions) -> Unit,
    onClickBack: () -> Unit
) {
    if (bookInfo == null) return
    val authorText =
        "지은이 ${bookInfo.authors.firstOrNull() ?: ""}" +
                (("외 ${bookInfo.authors.size - 1}명")
                    .takeIf { bookInfo.authors.size - 1 > 0 } ?: "")

    val translatorsText =
        "번역 ${bookInfo.translators.firstOrNull() ?: ""}" +
                (("외 ${bookInfo.translators.size - 1}명")
                    .takeIf { bookInfo.translators.size - 1 > 0 } ?: "")


    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                model = bookInfo.thumbnail,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "제목 : ${bookInfo.title}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "${authorText.takeIf { bookInfo.authors.isNotEmpty() } ?: " - "}  출판사 : ${bookInfo.publisher}",
                    fontSize = 16.sp,
                    color = Color.LightGray
                )


                if (bookInfo.translators.isNotEmpty()) {
                    Text(
                        text = translatorsText,
                        fontSize = 16.sp,
                        color = Color.LightGray
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    modifier = Modifier.align(Alignment.End),
                    text = bookInfo.price.toString(),
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.LineThrough
                )

                Text(
                    modifier = Modifier.align(Alignment.End),
                    fontSize = 24.sp,
                    text = "${bookInfo.salePrice} 원"
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "내용 : ${bookInfo.contents}",
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )


            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onClickBack() }) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.AutoMirrored.Sharp.ArrowBack,
                    contentDescription = null
                )
            }

            IconButton(onClick = { onActions(DetailActions.OnClickFavorite) }) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = if (bookInfo.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = if (bookInfo.isFavorite) Color.Red else Color.White
                )
            }
        }


    }
}


@Preview
@Composable
private fun Preview() {
    BookDetailScreen(
        bookInfo = BookInfo(
            title = "test",
            authors = listOf("홍길동"),
            translators = listOf(),
            salePrice = 10000,
            price = 12000,
            thumbnail = "",
            contents = "test content\ndsaddsa\ndksaldkalsdk",
            isFavorite = false,
            isbn = "123",
            status = "",
            publisher = "",
            datetime = "",
            url = "",
        ),
        onActions = {},
        onClickBack = {}
    )
}
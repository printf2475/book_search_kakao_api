package co.assignment.favorite.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.assignment.favorite.mapper.title
import co.assignment.model.SortType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortBottomSheetContent(
    isVisibilitySortBottomSheet: Boolean,
    sortType: SortType,
    onSelectSortType: (SortType) -> Unit,
    onCloseBottomSheet: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    if (isVisibilitySortBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            containerColor = Color.White,
            onDismissRequest = { onCloseBottomSheet() }
        ) {
            SheetContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
                sortType = sortType,
                onSelectedSortType = {
                    onSelectSortType(it)
                    scope
                        .launch { sheetState.hide() }
                        .invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onCloseBottomSheet()
                            }
                        }
                }
            )
        }
    }
}

@Composable
private fun SheetContent(
    modifier: Modifier = Modifier,
    sortType: SortType,
    onSelectedSortType: (SortType) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "정렬 기준",
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            SortType.entries.forEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelectedSortType(it) }
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) { Text(
                        text = it.title,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = if (it == sortType) Color.Blue.copy(0.7f) else Color.Black
                    )

                    if (it == sortType){
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color.Blue.copy(0.7f)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

    }
}


@Preview
@Composable
private fun PreviewSheetContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.BottomCenter
    ) {
        SheetContent(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            sortType = SortType.Asc,
            onSelectedSortType = {}
        )
    }
}
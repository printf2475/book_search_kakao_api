package co.assignment.favorite.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.assignment.favorite.mapper.title
import co.assignment.favorite.model.PriceFilterType
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceFilterBottomSheetContent(
    isVisibilityPriceFilterBottomSheet: Boolean,
    filterType: PriceFilterType?,
    onSelectPriceFilterType: (PriceFilterType?) -> Unit,
    onCloseBottomSheet: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    if (isVisibilityPriceFilterBottomSheet) {
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
                    .fillMaxHeight(0.7f),
                filterType = filterType,
                onSelectedPriceFilterType = {
                    onSelectPriceFilterType(it)
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
    filterType: PriceFilterType?,
    onSelectedPriceFilterType: (PriceFilterType?) -> Unit
) {
    var selectedPriceFilterType: PriceFilterType? by remember { mutableStateOf(filterType) }
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
                text = "가격",
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "가장 저렴한 책의 총금액 가격이에요.",
                fontSize = 16.sp,
                color = Color.LightGray
            )
            Spacer(modifier = Modifier.height(20.dp))

            PriceFilterType.entries.forEach {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { selectedPriceFilterType = it }
                        .padding(8.dp)
                        .height(24.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                color = if (it == selectedPriceFilterType) Color.Blue.copy(alpha = 0.7f) else Color.White,
                                shape = CircleShape
                            )
                            .border(1.dp, Color.LightGray, CircleShape)
                    )

                    Text(
                        modifier = Modifier.fillMaxHeight(),
                        text = it.title,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        HorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = { selectedPriceFilterType = null },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.Black
                )
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "초기화",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }

            TextButton(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(end = 20.dp),
                onClick = {
                    onSelectedPriceFilterType(selectedPriceFilterType)
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Blue.copy(alpha = 0.7f)
                )
            ) {
                Text(
                    text = "선택된 가격대의 책 보기",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
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
            filterType = PriceFilterType.Over20,
            onSelectedPriceFilterType = {}
        )

    }

}
package co.assignment.favorite.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.assignment.favorite.mapper.title
import co.assignment.favorite.model.PriceFilterType
import co.assignment.model.SortType



@Composable
fun FavoriteFilterButtonsContent(
    modifier: Modifier = Modifier,
    sortType: SortType,
    priceFilterType: PriceFilterType?,
    onClickSort: () -> Unit,
    onClickPriceFilter: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilterChip(
            selected = false,
            shape = RoundedCornerShape(24.dp),
            label = { Text(text = sortType.title) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                )
            },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color.Blue.copy(alpha = 0.7f),
                selectedLabelColor = Color.White,
                selectedTrailingIconColor = Color.White,
                containerColor = Color.LightGray,
                labelColor = Color.Black,
                iconColor = Color.Black,
            ),
            onClick = onClickSort,
        )

        FilterChip(
            selected = priceFilterType != null,
            shape = RoundedCornerShape(24.dp),
            label = { Text(text = priceFilterType?.title ?: "가격") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
            },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color.Blue.copy(alpha = 0.7f),
                selectedLabelColor = Color.White,
                selectedTrailingIconColor = Color.White,
                containerColor = Color.LightGray,
                labelColor = Color.Black,
                iconColor = Color.Black
            ),
            onClick = onClickPriceFilter,
        )
    }
}


@Preview
@Composable
private fun PreviewFavoriteFilterButtonsContent() {
    FavoriteFilterButtonsContent(
        modifier = Modifier,
        sortType = SortType.Asc,
        priceFilterType = PriceFilterType.Over20,
        onClickSort = {},
        onClickPriceFilter = {}
    )
}
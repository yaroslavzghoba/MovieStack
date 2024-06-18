package com.yaroslavzghoba.moviestack.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.yaroslavzghoba.moviestack.util.NavBarItem

@Composable
fun AppNavigationBar(
    items: List<NavBarItem>,
    selected: (Int) -> Boolean,
    onItemClicked: (NavBarItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        items.forEachIndexed { index, item ->
            val iconRes = when (selected(index)) {
                true -> item.selectedIconRes
                false -> item.unselectedIconRes
            }
            NavigationBarItem(
                selected = selected(index),
                onClick = { onItemClicked(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                    )
                },
                label = { Text(text = stringResource(id = item.titleRes)) },
            )
        }
    }
}
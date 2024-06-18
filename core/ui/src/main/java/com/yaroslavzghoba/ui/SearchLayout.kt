package com.yaroslavzghoba.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yaroslavzghoba.common.animationSpecDefault

@Composable
fun SearchLayout(
    searchLayoutState: SearchLayoutState,
    onActionButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    // Calculate top paddings
    val density = LocalDensity.current
    val topPadding = with(density) { WindowInsets.systemBars.getTop(density = density).toDp() }
    val innerTopPadding by animateDpAsState(
        targetValue = if (searchLayoutState.expanded) topPadding else 0.dp,
        animationSpec = animationSpecDefault(),
        label = "Search layout inner top padding",
    )

    // Clear focus after keyboard close
    val focusManager = LocalFocusManager.current
    val isKeyboardOpen = WindowInsets.ime.getBottom(density) > 0
    LaunchedEffect(isKeyboardOpen) {
        if (!isKeyboardOpen) focusManager.clearFocus()
    }

    val searchBarMinHeight = 56.dp
    val roundedCornerSize by animateDpAsState(
        targetValue = if (searchLayoutState.expanded) 0.dp else searchBarMinHeight / 2,
        animationSpec = animationSpecDefault(),
        label = "Search bar corners rounding size",
    )
    val textFieldState = rememberTextFieldState(initialText = searchLayoutState.query)
    val textFieldPlaceholder: @Composable () -> Unit = {
        Text(
            text = stringResource(id = R.string.search_bar_placeholder),
            style = MaterialTheme.typography.bodyLarge
                .copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
        )
    }
    val iconButton: @Composable () -> Unit = {
        IconButton(onClick = onActionButtonClicked) {
            Icon(
                painter = painterResource(
                    id = when (searchLayoutState.expanded) {
                        true -> R.drawable.baseline_arrow_back_24
                        false -> R.drawable.baseline_search_24
                    }
                ),
                contentDescription = stringResource(
                    id = when (searchLayoutState.expanded) {
                        true -> R.string.return_back_btn_description
                        false -> R.string.search_button_description
                    }
                ),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }
    }

    // Update layout state when text field state was changed
    LaunchedEffect(textFieldState.text.toString()) {
        searchLayoutState.query = textFieldState.text.toString()
    }

    Column(
        modifier = modifier
            .padding(top = topPadding - innerTopPadding)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHighest,
                shape = RoundedCornerShape(roundedCornerSize),
            )
            .clip(RoundedCornerShape(roundedCornerSize)),
    ) {
        BasicTextField(
            state = textFieldState,
            modifier = Modifier.padding(top = innerTopPadding),
            textStyle = MaterialTheme.typography.bodyLarge,
            decorator = { innerTextField ->
                Row(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    iconButton()
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        if (textFieldState.text.isEmpty()) textFieldPlaceholder()
                        innerTextField()
                    }
                }
            }
        )
        AnimatedVisibility(
            visible = searchLayoutState.expanded,
            enter = expandVertically(
                expandFrom = Alignment.Top,
                animationSpec = animationSpecDefault(),
            ),
            exit = shrinkVertically(
                animationSpec = animationSpecDefault(),
                shrinkTowards = Alignment.Bottom,
            ),
        ) {
            HorizontalDivider()
            content()
        }
    }
}

data class SearchLayoutState(
    private val expandedInitially: Boolean,
    private val initialQuery: String,
) {
    /**Is the search bar expanded now*/
    var expanded by mutableStateOf(expandedInitially)
        internal set

    /**Search query of the search bar*/
    var query by mutableStateOf(initialQuery)
        internal set

    /**Expand the search bar*/
    fun expand() {
        expanded = true
    }

    /**Collapse the search bar*/
    fun collapse() {
        expanded = false
    }

    companion object {
        val Saver: Saver<SearchLayoutState, List<String>> = Saver(
            save = { state: SearchLayoutState ->
                listOf(
                    state.expandedInitially.toString(),
                    state.initialQuery,
                )
            },
            restore = { values ->
                SearchLayoutState(
                    expandedInitially = values[0] == "true",
                    initialQuery = values[1]
                )
            }
        )
    }
}

@Composable
fun rememberSearchLayoutState(
    expanded: Boolean = false,
    query: String = "",
): SearchLayoutState {
    return rememberSaveable(saver = SearchLayoutState.Saver) {
        SearchLayoutState(
            expandedInitially = expanded,
            initialQuery = query,
        )
    }
}
package com.yaroslavzghoba.settings.component

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.yaroslavzghoba.settings.util.Option

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun <T> MultiOptionSelector(
    selectedOption: Option<T>?,
    onOptionSelected: (Option<T>) -> Unit,
    options: List<Option<T>>,
    modifier: Modifier = Modifier,
) {
    var isDropdownMenuExpanded by remember { mutableStateOf(false) }
    val themeModeFieldValue = selectedOption?.titleRes?.let { stringResource(id = it) } ?: ""
    ExposedDropdownMenuBox(
        expanded = isDropdownMenuExpanded,
        onExpandedChange = { isDropdownMenuExpanded = it },
        modifier = modifier,
    ) {
        OutlinedTextField(
            modifier = Modifier.menuAnchor(),
            value = themeModeFieldValue,
            onValueChange = { _ -> },
            readOnly = true,
            singleLine = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownMenuExpanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = isDropdownMenuExpanded,
            onDismissRequest = { isDropdownMenuExpanded = false },
        ) {
            options.forEach { value ->
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = value.titleRes)) },
                    onClick = {
                        onOptionSelected(value)
                        isDropdownMenuExpanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}
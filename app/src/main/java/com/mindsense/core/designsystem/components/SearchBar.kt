package com.mindsense.core.designsystem.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Pesquisar...",
) {
    InputField(
        value = value,
        onValueChange = onValueChange,
        label = "Busca",
        placeholder = placeholder,
        modifier = modifier,
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "Pesquisar",
            )
        },
    )
}

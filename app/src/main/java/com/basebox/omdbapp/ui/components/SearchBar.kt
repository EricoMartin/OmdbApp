package com.basebox.omdbapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier.fillMaxWidth()
            .height(56.dp),
        placeholder = { Text("Search", color = Color.Gray) },
        shape = RoundedCornerShape(24.dp),
        singleLine = true,
        trailingIcon = {
            if (query.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Cancel,
                    contentDescription = "Clear Search Icon",
                    tint = Color.Gray,
                    modifier = Modifier.clickable(
                        enabled = true,
                        interactionSource = null,
                        indication = null,
                        onClickLabel = "Cancel",
                        onClick = { onQueryChange("") })
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.DarkGray,
            unfocusedTextColor = Color.Gray,
            focusedContainerColor = Color(0xFFE6E6F0),
            unfocusedContainerColor = Color(0xFFE6E6F0),
            disabledContainerColor = Color(0xFFE6E6F0),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

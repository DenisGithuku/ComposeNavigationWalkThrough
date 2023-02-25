package com.githukudenis.composenavigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onOpenName: (String) -> Unit
) {

    val names by remember {
        mutableStateOf(
            listOf("Ann", "Peter", "Charlie", "Shem"))
    }

    Scaffold { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Text(text = "Welcome")
            }
            items(
                items = names,
                key = {
                    it
                }
            ) {
                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onClick = {
                        onOpenName(it)
                    }
                ) {
                    Column(modifier = modifier.padding(20.dp)) {
                        Text(
                            text = it,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen() {

    }
}
package com.infoapp.ui_components

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBarScreen(
    title: String,
    drawerState: DrawerState) {

    var stateFavorite by remember { mutableStateOf(false) }

    val imgFavorite = if (stateFavorite) {
        Icons.Default.Favorite
    } else {
        Icons.Default.FavoriteBorder
    }

    val scope = rememberCoroutineScope()

    TopAppBar(
        title = {
            Text(text = title)
        },
        modifier = Modifier
            .background(Color.White),
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isOpen) {
                                close()
                            } else {
                                open()
                            }
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Иконка меню")
            }
        },
        actions = {
            IconButton(
                onClick = {
                    stateFavorite = !stateFavorite
                }
            ) {
                Icon(
                    imageVector = imgFavorite,
                    contentDescription = "Иконка меню")
            }
        }
    )
}

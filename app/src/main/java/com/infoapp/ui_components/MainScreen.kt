package com.infoapp.ui_components

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.infoapp.utils.DrawerEvents
import com.infoapp.utils.IdArrayList
import com.infoapp.utils.ListItem
import kotlinx.coroutines.launch

@Composable
fun MainScreen(context: Context,
               onClick: (ListItem) -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var topBarTitle by remember {
        mutableStateOf("Грибы")
    }
    var mainList by remember {
        mutableStateOf(getListItemsByIndex(0, context))
    }
    val scope = rememberCoroutineScope()


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerMenuScreen(topBarTitle){ event ->
                when (event) {
                    is DrawerEvents.OnItemClick -> {
                        topBarTitle = event.title
                        mainList = getListItemsByIndex(
                            index = event.index,
                            context = context)
                    }
                }
                scope.launch {
                    drawerState.close()
                }
            }
        },
        content = {
            Scaffold (
                topBar = {
                    MainTopBarScreen(
                        title = topBarTitle,
                        drawerState = drawerState
                    )
                }
            ) { paddingValues ->
                LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding())
                ) {
                    items (mainList) { item ->
                        MainListItemScreen(item) { listItem ->
                            onClick(listItem)
                        }
                    }
                }
            }
        }
    )
}

private fun getListItemsByIndex(
    index: Int,
    context: Context
): List<ListItem> {
    val list = ArrayList<ListItem>()
    context.resources.getStringArray(IdArrayList.listId[index]).forEach { item ->
        val itemArray = item.split("|")
        list.add(
            ListItem(
                itemArray[0],
                itemArray[1],
                itemArray[2]
            )
        )
    }
    return list
}
package com.infoapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infoapp.ui.theme.InfoAppTheme
import com.infoapp.ui_components.DrawerMenu
import com.infoapp.ui_components.MainListItem
import com.infoapp.ui_components.MainTopBar
import com.infoapp.utils.DrawerEvents
import com.infoapp.utils.IdArrayList
import com.infoapp.utils.ListItem
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val topBarTitle = remember {
                mutableStateOf("Грибы")
            }
            val mainList = remember {
                mutableStateOf(getListItemsByIndex(0, this))
            }
            val scope = rememberCoroutineScope()
            InfoAppTheme {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        DrawerMenu(topBarTitle.value){ event ->
                            when (event) {
                                is DrawerEvents.OnItemClick -> {
                                    topBarTitle.value = event.title
                                    mainList.value = getListItemsByIndex(
                                        index = event.index,
                                        context = this@MainActivity)
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
                                MainTopBar(
                                    title = topBarTitle.value,
                                    drawerState = drawerState
                                )
                            }
                        ) { paddingValues ->
                            LazyColumn(modifier = Modifier.fillMaxSize()
                                .padding(top = 130.dp)
                            ) {
                                items (mainList.value) { item ->
                                    MainListItem(item)
                                }
                            }
                        }
                    }
                )
            }
        }
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
                    itemArray[1]
                )
            )
        }
        return list
    }
}
package com.infoapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.infoapp.ui.theme.InfoAppTheme
import com.infoapp.ui_components.DrawerMenu
import com.infoapp.ui_components.MainTopBar
import com.infoapp.utils.DrawerEvents
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
            val scope = rememberCoroutineScope()
            InfoAppTheme {
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        DrawerMenu(){ event ->
                            when (event) {
                                is DrawerEvents.OnItemClick -> {
                                    topBarTitle.value = event.title
                                    scope.launch {
                                        drawerState.close()
                                    }
                                }
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
                            },
                            content = {

                            }
                        )
                    }
                )
            }
        }
    }
}
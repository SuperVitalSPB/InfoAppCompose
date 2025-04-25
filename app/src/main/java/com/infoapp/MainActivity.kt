package com.infoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.infoapp.ui.theme.InfoAppTheme
import com.infoapp.ui_components.MainScreen
import com.infoapp.utils.Routes
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.infoapp.ui_components.InfoScreen
import com.infoapp.utils.ListItem

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var item: ListItem? = null
            InfoAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Routes.MAIN_SCREEN) {
                    composable(Routes.MAIN_SCREEN) {
                        MainScreen(this@MainActivity) { listItem ->
                            item = listItem
                            navController.navigate(Routes.INFO_SCREEN)
                        }
                    }
                    composable(Routes.INFO_SCREEN) {
                        item?.let {
                            InfoScreen(it)
                        }
                    }
                }
            }
        }
    }
}
package com.example.koreancompose.screens.favoritesscreen

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.koreancompose.*
import com.example.koreancompose.R
import com.example.koreancompose.database.StoredItem
import com.example.koreancompose.viewmodels.StoredItemsViewModel
import com.example.koreancompose.screens.sidedrawerscreen.SideDrawer


@SuppressLint("UnrememberedMutableState")
@Composable
fun FavoritesScreen(
    viewModel: StoredItemsViewModel =
        StoredItemsViewModel(LocalContext.current.applicationContext as Application),
    navController: NavController,
    focusManager: FocusManager
) {
    val allItems by viewModel.readAllData.observeAsState(mutableListOf())
//For the scaffold
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                scope = scope,
                scaffoldState = scaffoldState,
                viewModel = com.example.koreancompose.viewModel,
                focusManager = focusManager
            )
        },
        drawerBackgroundColor = colorResource(id = R.color.purple_200),
        // scrimColor = Color.Red,  // Color for the fade background when you open/close the drawer
        drawerContent = {
            SideDrawer(
                scaffoldState = scaffoldState,
                navController = navController,
                viewModel = com.example.koreancompose.viewModel
            )
        },
    ) {
        SwipeTest(allItems as MutableList<StoredItem>, navController = navController)
    }
}


package com.example.koreancompose.screens.sidedrawerscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.koreancompose.Screen
import kotlinx.coroutines.launch

enum class Items(val label: String, val icon: ImageVector) {
    Favorites(label = "Favorites", Icons.Default.Home),
    Words(label = "Words", Icons.Default.Email),
    Grammar(label = "Grammar", Icons.Default.Favorite)
}
@Composable
fun SideDrawer(scaffoldState: ScaffoldState, navController: NavController) {

    val scope = rememberCoroutineScope()
    var text by remember {
        mutableStateOf("")
    }
    ModalDrawer(
        drawerState = scaffoldState.drawerState,
        gesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            Header(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp))
            Body(items = Items.values().toList(), ) {
                when(it) {
                    Items.Grammar -> {
                        navController.navigate(Screen.GrammarListScreen.route)
                    }
                    Items.Favorites -> {
                        navController.navigate(Screen.FavoritesScreen.route)
                    }
                    Items.Words -> {
                        navController.navigate(Screen.WordListScreen.route)
                    }
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        },

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
//            Button(onClick = { scope.launch { drawerState.open() } } ){
//                Text(text = "Open Drawer")
//            }
            Text(text = text)

        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Default.Person, contentDescription = null)
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = "My Account")
    }
}

@Composable
fun Body(items:List<Items>, onClickItem: (Items) -> Unit) {
    Column(modifier = Modifier.padding(8.dp)) {
        items.forEach {
            Row(modifier = Modifier.clickable {
                onClickItem.invoke(it)
            }) {
                Icon(imageVector = it.icon, contentDescription = null )
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = it.label)
            }
            Spacer(modifier = Modifier.size(8.dp))
        }

    }
}
//@Preview(showSystemUi = true)
//@Composable
//fun preview() {
//    SideDrawer()
//}
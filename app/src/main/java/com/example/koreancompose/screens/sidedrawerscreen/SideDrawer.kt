package com.example.koreancompose.screens.sidedrawerscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.koreancompose.Screen
import com.example.koreancompose.ui.theme.PrimaryOrange
import com.example.koreancompose.ui.theme.spacing
import com.example.koreancompose.viewmodels.ViewModel
import com.jagerapps.koreancompose.R
import kotlinx.coroutines.launch

enum class Items(val label: String, val icon: Int) {
    Practice(label = "Practice", R.drawable.edit),
    Favorites(label = "Favorites", R.drawable.ic_favorite_filled),
    Words(label = "Words", R.drawable.book),
    Grammar(label = "Grammar", R.drawable.writing)
}

@Composable
fun SideDrawer(scaffoldState: ScaffoldState, navController: NavController, viewModel: ViewModel) {

    val scope = rememberCoroutineScope()
    ModalDrawer(
        drawerState = scaffoldState.drawerState,
        gesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerBackgroundColor = MaterialTheme.colors.secondary,
        drawerContent = {
            Header(
                modifier = Modifier
                    .fillMaxWidth()
            )
            Body(items = Items.values().toList()) {
                when (it) {
                    Items.Grammar -> {
                        navController.navigate(Screen.GrammarListScreen.route)
                        viewModel.topBarText.value = "Grammar"
                    }
                    Items.Favorites -> {
                        navController.navigate(Screen.FavoritesScreen.route)
                        viewModel.topBarText.value = "Favorites"

                    }
                    Items.Words -> {
                        navController.navigate(Screen.WordListScreen.route)
                        viewModel.topBarText.value = "Words"
                    }
                    Items.Practice -> {
                        navController.navigate(Screen.PracticeScreen.route)
                        viewModel.topBarText.value = "Practice"
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
            Text(text = "text")

        }
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = MaterialTheme.spacing.large)
    ) {
        Image(
            modifier = Modifier.size(150.dp),
            painter = painterResource(R.drawable.logo),
            contentDescription = null
        )
        Divider(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxWidth()
                .height(0.5.dp),
            color = Color.LightGray
        )
    }
}

@Composable
fun Body(items: List<Items>, onClickItem: (Items) -> Unit) {
    Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
        items.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.small,
                        top = MaterialTheme.spacing.extraSmall,
                        bottom = MaterialTheme.spacing.extraSmall
                    )
                    .clickable {
                        onClickItem.invoke(it)
                    }) {
                Icon(
                    modifier = Modifier
                        .padding(end = MaterialTheme.spacing.extraSmall)
                        .size(20.dp),
                    painter = painterResource(id = it.icon),
                    tint = MaterialTheme.colors.primaryVariant,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    style = MaterialTheme.typography.h1,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    text = it.label
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
        }

    }
}
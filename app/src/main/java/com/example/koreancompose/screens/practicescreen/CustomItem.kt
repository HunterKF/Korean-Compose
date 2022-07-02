package com.example.koreancompose

import android.app.Application
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.koreancompose.viewmodels.StoredItemsViewModel
import com.example.koreancompose.model.PracticeCard
import com.example.koreancompose.screens.practicescreen.CustomItemFuns.FavoriteFun
import com.example.koreancompose.screens.practicescreen.CustomItemFuns.InfoFun
import com.example.koreancompose.screens.practicescreen.CustomItemFuns.ShareFun
import com.example.koreancompose.ui.theme.Typography
import com.example.koreancompose.ui.theme.elevation
import com.example.koreancompose.ui.theme.spacing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomItem(
    practiceCard: PracticeCard,
    navController: NavController,
    expandedState: Boolean,
    onClick: () -> Unit,
    focusManager: FocusManager,
    modifier: Modifier
) {
    //For the share sheet
    val appName = "Korean Practice"
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            "Check out what I wrote in $appName! ${practiceCard.inputtedSentence}"
        )
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    val context = LocalContext.current
    val application = context.applicationContext as Application

    val storedItemsViewModel = StoredItemsViewModel(application)
    val searchResults by storedItemsViewModel.searchResults.observeAsState(listOf())

    storedItemsViewModel.searchStoredItem(practiceCard.inputtedSentence)


    Column(
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.small)
            .shadow(elevation = MaterialTheme.elevation.small, shape = RoundedCornerShape(16.dp))
            .clip(shape = RoundedCornerShape(10.dp))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = Color.White,
            shape = RoundedCornerShape(10.dp),
            onClick = onClick
        ) {
            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .fillMaxWidth()
            ) {

                Text(
                    modifier = Modifier,
                    text = practiceCard.inputtedSentence,
                    style = MaterialTheme.typography.body2

                )



                if (expandedState) {
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium)/*.animateContentSize()*/)
                    Divider(modifier = Modifier.height(1.dp))
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        ShareFun(context, shareIntent)
                        FavoriteFun(
                            practiceCard,
                            searchResults,
                            storedItemsViewModel = storedItemsViewModel
                        )
                        InfoFun(practiceCard, navController)
                    }
                }
            }


        }
    }

}

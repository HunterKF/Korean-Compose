package com.example.koreancompose

import android.app.Application
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.koreancompose.database.StoredItem
import com.example.koreancompose.database.StoredItemsViewModel
import com.example.koreancompose.model.PracticeCard
import com.example.koreancompose.screens.practicescreen.CustomItemFuns.FavoriteFun
import com.example.koreancompose.screens.practicescreen.CustomItemFuns.InfoFun
import com.example.koreancompose.screens.practicescreen.CustomItemFuns.ShareFun
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomItem(
    practiceCard: PracticeCard,
    navController: NavController,
    expandedState: Boolean,
    onClick: () -> Unit,
    focusManager: FocusManager
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


    Column(modifier = Modifier.padding(10.dp)) {
        Card(
            modifier = Modifier
                .shadow(2.dp, RoundedCornerShape(16.dp), true)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            onClick = onClick
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Row {
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = practiceCard.word,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(
                            modifier = Modifier
                                .height(10.dp)
                        )
                        Text(
                            text = practiceCard.grammar,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Light
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .width(1.dp)
                    )
                    Text(
                        modifier = Modifier
                            .weight(4f),
                        text = practiceCard.inputtedSentence,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        letterSpacing = 0.15.sp

                    )

                }

                if (expandedState) {
                    Spacer(modifier = Modifier.height(8.dp)/*.animateContentSize()*/)
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        ShareFun(context, shareIntent)
                        FavoriteFun(practiceCard, searchResults, storedItemsViewModel = storedItemsViewModel)
                        InfoFun(practiceCard, navController)
                    }
                }
            }


        }
    }

}

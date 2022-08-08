package com.example.tablayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.screen.screens
import com.example.tablayout.ui.theme.TabLayoutTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabsApp()
        }
    }
}


@Composable
fun TabsApp() {
    TabLayoutTheme {
        MainScreen()
    }
}


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(topBar = { SmallTopAppBar(title = { Text(text = "Tabs Sample") }) })
    { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues = paddingValues)) {
            Tabs()
        }
    }
}


@ExperimentalPagerApi
@Composable
fun Tabs() {
    val pageState = rememberPagerState()
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pageState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(
                    pagerState = pageState,
                    tabPositions = tabPositions
                )
            )
        },
    ) {

        screens.forEachIndexed { index, screen ->
            Tab(
                text = {
                    Text(text = screen.route)
                },
                icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                selected = pageState.currentPage == index,
                onClick = {
                    scope.launch {
                        pageState.animateScrollToPage(index)
                    }
                },
            )
        }
    }

    HorizontalPager(count = screens.size, state = pageState) { index ->
        Column(modifier = Modifier.fillMaxSize()) {
            screens[index].content.invoke()
        }
    }
}

@Composable
fun HomeScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF00BCD4))
    ) {
        Text(text = "Home Screen")
    }
}

@Composable
fun FavoriteScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF8BC34A))
    ) {
        Text(text = "Favorite Screen")
    }
}

@Composable
fun SearchScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFC107))
    ) {
        Text(text = "Search Screen")
    }
}
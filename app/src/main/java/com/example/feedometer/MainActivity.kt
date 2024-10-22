package com.example.feedometer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.feedometer.naviagtion.NavGraph
import com.example.feedometer.screens.HomeScreen
import com.example.feedometer.screens.ProfileScreen
import com.example.feedometer.screens.SearchScreen
import com.example.feedometer.ui.theme.FeedometerTheme
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.material.navigation.NavigationBarItemView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FeedometerTheme {
                val navController = rememberNavController()
                NavGraph(navController, activity = this)
                SetStatusBarColor()
            }
        }
    }
}
@Composable
fun BottomNav() {
    var selectedIndex by remember { mutableStateOf(0) }

    val navigationItems = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = R.drawable.ic_home_filled,
            unselectedIcon = R.drawable.ic_home_outlined
        ),
        NavigationItem(
            title = "Search",
            selectedIcon = R.drawable.ic_search_filled,
            unselectedIcon = R.drawable.ic_search_outlined
        ),
        NavigationItem(
            title = "Profile",
            selectedIcon = R.drawable.ic_profile_filled,
            unselectedIcon = R.drawable.ic_profile_outlined
        )
    )

    Scaffold(
        bottomBar = {
            AnimatedNavigationBar(
                modifier = Modifier.height(64.dp),
                selectedIndex = selectedIndex,
                cornerRadius = shapeCornerRadius(34.dp),
                ballAnimation = Parabolic(tween(300)),
                indentAnimation = Height(tween(300)),
                barColor = MaterialTheme.colorScheme.primaryContainer,
                ballColor = MaterialTheme.colorScheme.primary
            ) {
                navigationItems.forEachIndexed { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .noRippleClickable { selectedIndex = index }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (selectedIndex == index) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                }
                            ),
                            contentDescription = item.title,
                            tint = if (selectedIndex == index) {
                                MaterialTheme.colorScheme.onPrimary
                            } else {
                                MaterialTheme.colorScheme.onPrimaryContainer
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (selectedIndex) {
                0 -> HomeScreen()
                1 -> SearchScreen()
                2 -> ProfileScreen()
            }
        }
    }
}

data class NavigationItem(
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int
)

@Composable
fun noRippleClickable(onClick: () -> Unit) = Modifier.clickable(
    interactionSource = remember { MutableInteractionSource() },
    indication = null,
    onClick = onClick
)












@Composable
fun SetStatusBarColor() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = true

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.White,
            darkIcons = useDarkIcons
        )
    }
}

@Preview
@Composable
fun PreviewMainActivity() {
    FeedometerTheme {
        SetStatusBarColor()
    }
}

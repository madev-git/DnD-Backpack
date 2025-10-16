package com.mad.tabletopbackpack.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mad.tabletopbackpack.ui.component.BackpackAppBar
import com.mad.tabletopbackpack.ui.component.BackpackBottomNavigationBar
import com.mad.tabletopbackpack.ui.theme.TabletopBackpackTheme

@Composable
fun BackpackApp(
    viewModel: BackpackViewModel = viewModel(factory = BackpackViewModel.Factory),
    windowSize: WindowWidthSizeClass,
) {
    val backpackUiState = viewModel.uiState.collectAsState().value
    BackPackScreen(windowSize = windowSize, backpackUiState = backpackUiState)
}

// this composable is here to allow preview while using ViewModel
@Composable
fun BackPackScreen(
    windowSize: WindowWidthSizeClass,
    backpackUiState: BackpackUiState,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        BackpackScreenEnum.valueOf(
            backStackEntry?.destination?.route ?: BackpackScreenEnum.Character.name,
        )

    Scaffold(
        topBar = {
            BackpackAppBar(
                currentScreen = currentScreen,
                canNavigateBack =
                    navController.previousBackStackEntry != null &&
                        screenIsAllowedToHaveBackButton(
                            currentScreen,
                        ),
                navigateUp = { navController.navigateUp() },
            )
        },
        bottomBar = {
            BackpackBottomNavigationBar(
                onClick = {
                    navController.navigate(route = it)
                },
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BackpackScreenEnum.Character.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = BackpackScreenEnum.Character.name) {
                CharacterScreen(
                    backpackUiState = backpackUiState,
                    modifier =
                        Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                )
            }
            composable(route = BackpackScreenEnum.Inventory.name) {
                InventoryScreen(
                    backpackUiState = backpackUiState,
                    modifier =
                        Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                )
            }
            composable(route = BackpackScreenEnum.Resources.name) {
                ResourcesScreen(
                    backpackUiState = backpackUiState,
                    modifier =
                        Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                )
            }
            composable(route = BackpackScreenEnum.Story.name) {
                StoryScreen(
                    backpackUiState = backpackUiState,
                    modifier =
                        Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BackpackMainScreenCompactPreview() {
    TabletopBackpackTheme {
        Surface {
            BackPackScreen(
                windowSize = WindowWidthSizeClass.Compact,
                backpackUiState = BackpackUiState(),
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
private fun BackpackMainScreenMediumPreview() {
    TabletopBackpackTheme {
        Surface {
            BackPackScreen(
                windowSize = WindowWidthSizeClass.Medium,
                backpackUiState = BackpackUiState(),
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
private fun BackpackMainScreenExpandedPreview() {
    TabletopBackpackTheme {
        Surface {
            BackPackScreen(
                windowSize = WindowWidthSizeClass.Expanded,
                backpackUiState = BackpackUiState(),
            )
        }
    }
}

private fun screenIsAllowedToHaveBackButton(screen: BackpackScreenEnum): Boolean =
    (
        screen.name != BackpackScreenEnum.Character.name && screen.name != BackpackScreenEnum.Inventory.name &&
            screen.name != BackpackScreenEnum.Resources.name && screen.name != BackpackScreenEnum.Story.name
    )

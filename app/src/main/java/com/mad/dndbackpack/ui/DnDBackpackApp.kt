package com.mad.dndbackpack.ui

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
import com.mad.dndbackpack.ui.component.BackpackAppBar
import com.mad.dndbackpack.ui.component.BackpackBottomNavigationBar
import com.mad.dndbackpack.ui.theme.DnDBackpackTheme

@Composable
fun BackpackApp(
    viewModel: DnDBackpackViewModel = viewModel(factory = DnDBackpackViewModel.Factory),
    windowSize: WindowWidthSizeClass,
) {
    val backpackUiState = viewModel.uiState.collectAsState().value
    BackPackScreen(windowSize = windowSize, dnDBackpackUiState = backpackUiState)
}

// this composable is here to allow preview while using ViewModel
@Composable
fun BackPackScreen(
    windowSize: WindowWidthSizeClass,
    dnDBackpackUiState: DnDBackpackUiState,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        DnDBackpackScreenEnum.valueOf(
            backStackEntry?.destination?.route ?: DnDBackpackScreenEnum.Character.name,
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
            startDestination = DnDBackpackScreenEnum.Character.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = DnDBackpackScreenEnum.Character.name) {
                CharacterScreen(
                    dnDBackpackUiState = dnDBackpackUiState,
                    modifier =
                        Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                )
            }
            composable(route = DnDBackpackScreenEnum.Inventory.name) {
                InventoryScreen(
                    dnDBackpackUiState = dnDBackpackUiState,
                    modifier =
                        Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                )
            }
            composable(route = DnDBackpackScreenEnum.Resources.name) {
                ResourcesScreen(
                    dnDBackpackUiState = dnDBackpackUiState,
                    modifier =
                        Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                )
            }
            composable(route = DnDBackpackScreenEnum.Story.name) {
                StoryScreen(
                    dnDBackpackUiState = dnDBackpackUiState,
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
    DnDBackpackTheme {
        Surface {
            BackPackScreen(
                windowSize = WindowWidthSizeClass.Compact,
                dnDBackpackUiState = DnDBackpackUiState(),
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
private fun BackpackMainScreenMediumPreview() {
    DnDBackpackTheme {
        Surface {
            BackPackScreen(
                windowSize = WindowWidthSizeClass.Medium,
                dnDBackpackUiState = DnDBackpackUiState(),
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
private fun BackpackMainScreenExpandedPreview() {
    DnDBackpackTheme {
        Surface {
            BackPackScreen(
                windowSize = WindowWidthSizeClass.Expanded,
                dnDBackpackUiState = DnDBackpackUiState(),
            )
        }
    }
}

private fun screenIsAllowedToHaveBackButton(screen: DnDBackpackScreenEnum): Boolean =
    (
        screen.name != DnDBackpackScreenEnum.Character.name && screen.name != DnDBackpackScreenEnum.Inventory.name &&
            screen.name != DnDBackpackScreenEnum.Resources.name && screen.name != DnDBackpackScreenEnum.Story.name
    )

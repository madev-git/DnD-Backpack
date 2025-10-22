package com.mad.dndbackpack.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mad.dndbackpack.R
import com.mad.dndbackpack.ui.component.DnDBackpackAppBar
import com.mad.dndbackpack.ui.component.DnDBackpackBottomNavigationBar
import com.mad.dndbackpack.ui.component.DnDBackpackNavigationDrawerContent
import com.mad.dndbackpack.ui.component.DnDBackpackNavigationRail
import com.mad.dndbackpack.ui.theme.DnDBackpackTheme
import com.mad.dndbackpack.ui.utils.DnDBackpackNavigationType
import com.mad.dndbackpack.ui.utils.DnDBackpackScreenEnum

@Composable
fun DnDBackpackApp(
    viewModel: DnDBackpackViewModel = viewModel(factory = DnDBackpackViewModel.Factory),
    windowSize: WindowWidthSizeClass,
) {
    val backpackUiState = viewModel.uiState.collectAsState().value
    DnDBackPackScreen(windowSize = windowSize, dnDBackpackUiState = backpackUiState)
}

// this composable is here to allow preview while using ViewModel
@Composable
fun DnDBackPackScreen(
    windowSize: WindowWidthSizeClass,
    dnDBackpackUiState: DnDBackpackUiState,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        DnDBackpackScreenEnum.valueOf(
            backStackEntry?.destination?.route ?: DnDBackpackScreenEnum.Character.name,
        )

    val navigationType: DnDBackpackNavigationType
    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = DnDBackpackNavigationType.BOTTOM_NAVIGATION
        }

        WindowWidthSizeClass.Medium -> {
            navigationType = DnDBackpackNavigationType.NAVIGATION_RAIL
        }

        WindowWidthSizeClass.Expanded -> {
            navigationType = DnDBackpackNavigationType.PERMANENT_NAVIGATION_DRAWER
        }

        else -> {
            navigationType = DnDBackpackNavigationType.BOTTOM_NAVIGATION
        }
    }

    Scaffold(
        topBar = {
            DnDBackpackAppBar(
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
            AnimatedVisibility(visible = navigationType == DnDBackpackNavigationType.BOTTOM_NAVIGATION) {
                val bottomNavigationContentDescription = stringResource(R.string.navigation_bottom)
                DnDBackpackBottomNavigationBar(
                    onClick = {
                        navController.navigate(route = it)
                    },
                    modifier = Modifier.testTag(bottomNavigationContentDescription),
                )
            }
        },
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (navigationType) {
                DnDBackpackNavigationType.NAVIGATION_RAIL -> {
                    Row(modifier = Modifier.fillMaxSize()) {
                        AnimatedVisibility(visible = true) {
                            val navigationRailContentDescription =
                                stringResource(R.string.navigation_rail)
                            DnDBackpackNavigationRail(
                                onClick = {
                                    navController.navigate(route = it)
                                },
                                containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                                modifier = Modifier.testTag(navigationRailContentDescription),
                            )
                        }
                        DnDBackpackScreenContent(
                            dnDBackpackUiState = dnDBackpackUiState,
                            navController = navController,
                        )
                    }
                }

                DnDBackpackNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
                    val navigationDrawerContentDescription =
                        stringResource(R.string.navigation_drawer)
                    PermanentNavigationDrawer(
                        drawerContent = {
                            PermanentDrawerSheet(
                                modifier = Modifier.width(dimensionResource(R.dimen.drawer_width)),
                                drawerContainerColor = MaterialTheme.colorScheme.inverseOnSurface,
                            ) {
                                DnDBackpackNavigationDrawerContent(
                                    onClick = {
                                        navController.navigate(route = it)
                                    },
                                    modifier =
                                        Modifier
                                            .wrapContentWidth()
                                            .fillMaxHeight()
                                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                                            .padding(dimensionResource(R.dimen.drawer_padding_content)),
                                )
                            }
                        },
                        modifier = Modifier.testTag(navigationDrawerContentDescription),
                    ) {
                        DnDBackpackScreenContent(
                            dnDBackpackUiState = dnDBackpackUiState,
                            navController = navController,
                        )
                    }
                }

                else -> {
                    DnDBackpackScreenContent(
                        dnDBackpackUiState = dnDBackpackUiState,
                        navController = navController,
                    )
                }
            }
        }
    }
}

@Composable
fun DnDBackpackScreenContent(
    dnDBackpackUiState: DnDBackpackUiState,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = DnDBackpackScreenEnum.Character.name,
    ) {
        composable(route = DnDBackpackScreenEnum.Character.name) {
            CharacterScreen(
                dnDBackpackUiState = dnDBackpackUiState,
                modifier = Modifier.fillMaxSize(),
            )
        }
        composable(route = DnDBackpackScreenEnum.Inventory.name) {
            InventoryScreen(
                dnDBackpackUiState = dnDBackpackUiState,
                modifier = Modifier.fillMaxSize(),
            )
        }
        composable(route = DnDBackpackScreenEnum.Resources.name) {
            ResourcesScreen(
                dnDBackpackUiState = dnDBackpackUiState,
                modifier = Modifier.fillMaxSize(),
            )
        }
        composable(route = DnDBackpackScreenEnum.Story.name) {
            StoryScreen(
                dnDBackpackUiState = dnDBackpackUiState,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DnDBackpackMainScreenCompactPreview() {
    DnDBackpackTheme {
        Surface {
            DnDBackPackScreen(
                windowSize = WindowWidthSizeClass.Compact,
                dnDBackpackUiState = DnDBackpackUiState(),
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
private fun DnDBackpackMainScreenMediumPreview() {
    DnDBackpackTheme {
        Surface {
            DnDBackPackScreen(
                windowSize = WindowWidthSizeClass.Medium,
                dnDBackpackUiState = DnDBackpackUiState(),
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
private fun DnDBackpackMainScreenExpandedPreview() {
    DnDBackpackTheme {
        Surface {
            DnDBackPackScreen(
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

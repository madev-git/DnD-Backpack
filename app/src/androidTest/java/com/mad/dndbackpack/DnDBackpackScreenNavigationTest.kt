package com.mad.dndbackpack

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.mad.dndbackpack.ui.BackPackScreen
import com.mad.dndbackpack.ui.DnDBackpackScreenEnum
import com.mad.dndbackpack.ui.DnDBackpackUiState
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DnDBackpackScreenNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupBackpackCompactNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            BackPackScreen(
                windowSize = WindowWidthSizeClass.Companion.Compact,
                dnDBackpackUiState = DnDBackpackUiState(),
                navController = navController
            )
        }
    }

    @Test
    fun backpackNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Character.name)
    }

    @Test
    fun backpackNavHost_verifyBackNavigationNotShownOnCharacterScreen() {
        assertNoBackButton()
    }

    @Test
    fun backpackNavHost_bottomBarClickInventoryItem_navigateToInventoryScreen() {
        navigateToInventoryScreen()
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Inventory.name)
    }

    @Test
    fun backpackNavHost_verifyBackNavigationNotShownOnInventoryScreen() {
        navigateToInventoryScreen()
        assertNoBackButton()
    }

    @Test
    fun backpackNavHost_bottomBarClickCharacterItem_navigateToCharacterScreen() {
        navigateToInventoryScreen()
        navigateToCharacterScreen()
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Character.name)
    }

    @Test
    fun backpackNavHost_bottomBarClickResourcesItem_navigateToResourcesScreen() {
        navigateToResourcesScreen()
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Resources.name)
    }

    @Test
    fun backpackNavHost_verifyBackNavigationNotShownOnResourcesScreen() {
        navigateToResourcesScreen()
        assertNoBackButton()
    }

    @Test
    fun backpackNavHost_bottomBarClickStoryItem_navigateToStoryScreen() {
        navigateToStoryScreen()
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Story.name)
    }

    @Test
    fun backpackNavHost_verifyBackNavigationNotShownOnStoryScreen() {
        navigateToStoryScreen()
        assertNoBackButton()
    }

    private fun navigateToCharacterScreen() {
        composeTestRule.onNodeWithStringId(R.string.character_screen_title).performClick()
    }

    private fun navigateToInventoryScreen() {
        composeTestRule.onNodeWithStringId(R.string.inventory_screen_title).performClick()
    }

    private fun navigateToResourcesScreen() {
        composeTestRule.onNodeWithStringId(R.string.resources_screen_title).performClick()
    }

    private fun navigateToStoryScreen() {
        composeTestRule.onNodeWithStringId(R.string.story_screen_title).performClick()
    }

    private fun assertNoBackButton() {
        composeTestRule.onNodeWithContentDescriptionStringId(R.string.back_button)
            .assertDoesNotExist()
    }
}
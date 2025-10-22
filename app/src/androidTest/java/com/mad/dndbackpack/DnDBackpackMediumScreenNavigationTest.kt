package com.mad.dndbackpack

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.performClick
import com.mad.dndbackpack.ui.DnDBackPackScreen
import com.mad.dndbackpack.ui.DnDBackpackUiState
import com.mad.dndbackpack.ui.utils.DnDBackpackScreenEnum
import org.junit.Before
import org.junit.Test

class DnDBackpackMediumScreenNavigationTest : DnDBackpackBaseNavigationTest() {

    @Before
    fun setupBackpackCompactNavHost() {
        composeTestRule.setContent {
            setupBackpackNavHost(LocalContext.current)
            DnDBackPackScreen(
                windowSize = WindowWidthSizeClass.Companion.Medium,
                dnDBackpackUiState = DnDBackpackUiState(),
                navController = navController
            )
        }
    }

    @Test
    @TestMediumWidth
    fun backpackNavHost_verifyUsingNavigationRail() {
        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_rail
        ).assertExists()
    }

    @Test
    @TestMediumWidth
    fun backpackNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Character.name)
    }

    @Test
    @TestMediumWidth
    fun backpackNavHost_verifyBackNavigationNotShownOnCharacterScreen() {
        assertNoBackButton()
    }

    @Test
    @TestMediumWidth
    fun backpackNavHost_navigationRailClickInventoryItem_navigateToInventoryScreen() {
        navigateToInventoryScreen()
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Inventory.name)
    }

    @Test
    @TestMediumWidth
    fun backpackNavHost_verifyBackNavigationNotShownOnInventoryScreen() {
        navigateToInventoryScreen()
        assertNoBackButton()
    }

    @Test
    @TestMediumWidth
    fun backpackNavHost_navigationRailClickCharacterItem_navigateToCharacterScreen() {
        navigateToInventoryScreen()
        navigateToCharacterScreen()
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Character.name)
    }

    @Test
    @TestMediumWidth
    fun backpackNavHost_navigationRailClickResourcesItem_navigateToResourcesScreen() {
        navigateToResourcesScreen()
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Resources.name)
    }

    @Test
    @TestMediumWidth
    fun backpackNavHost_verifyBackNavigationNotShownOnResourcesScreen() {
        navigateToResourcesScreen()
        assertNoBackButton()
    }

    @Test
    @TestMediumWidth
    fun backpackNavHost_navigationRailClickStoryItem_navigateToStoryScreen() {
        navigateToStoryScreen()
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Story.name)
    }

    @Test
    @TestMediumWidth
    fun backpackNavHost_verifyBackNavigationNotShownOnStoryScreen() {
        navigateToStoryScreen()
        assertNoBackButton()
    }

    override fun navigateToCharacterScreen() {
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.character_screen_title)
            .performClick()
    }

    override fun navigateToInventoryScreen() {
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.inventory_screen_title)
            .performClick()
    }

    override fun navigateToResourcesScreen() {
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.resources_screen_title)
            .performClick()
    }

    override fun navigateToStoryScreen() {
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.story_screen_title)
            .performClick()
    }
}
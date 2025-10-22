package com.mad.dndbackpack


import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.performClick
import com.mad.dndbackpack.ui.DnDBackPackScreen
import com.mad.dndbackpack.ui.DnDBackpackUiState
import com.mad.dndbackpack.ui.utils.DnDBackpackScreenEnum
import org.junit.Before
import org.junit.Test

class DnDBackpackCompactScreenNavigationTest : DnDBackpackBaseNavigationTest() {

    @Before
    fun setupBackpackCompactNavHost() {
        composeTestRule.setContent {
            setupBackpackNavHost(LocalContext.current)
            DnDBackPackScreen(
                windowSize = WindowWidthSizeClass.Companion.Compact,
                dnDBackpackUiState = DnDBackpackUiState(),
                navController = navController
            )
        }
    }

    @Test
    @TestCompactWidth
    fun backpackNavHost_verifyUsingBottomNavigation() {
        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_bottom
        ).assertExists()
    }

    @Test
    @TestCompactWidth
    fun backpackNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Character.name)
    }

    @Test
    @TestCompactWidth
    fun backpackNavHost_verifyBackNavigationNotShownOnCharacterScreen() {
        assertNoBackButton()
    }

    @Test
    @TestCompactWidth
    fun backpackNavHost_bottomBarClickInventoryItem_navigateToInventoryScreen() {
        navigateToInventoryScreen()
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Inventory.name)
    }

    @Test
    @TestCompactWidth
    fun backpackNavHost_verifyBackNavigationNotShownOnInventoryScreen() {
        navigateToInventoryScreen()
        assertNoBackButton()
    }

    @Test
    @TestCompactWidth
    fun backpackNavHost_bottomBarClickCharacterItem_navigateToCharacterScreen() {
        navigateToInventoryScreen()
        navigateToCharacterScreen()
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Character.name)
    }

    @Test
    @TestCompactWidth
    fun backpackNavHost_bottomBarClickResourcesItem_navigateToResourcesScreen() {
        navigateToResourcesScreen()
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Resources.name)
    }

    @Test
    @TestCompactWidth
    fun backpackNavHost_verifyBackNavigationNotShownOnResourcesScreen() {
        navigateToResourcesScreen()
        assertNoBackButton()
    }

    @Test
    @TestCompactWidth
    fun backpackNavHost_bottomBarClickStoryItem_navigateToStoryScreen() {
        navigateToStoryScreen()
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Story.name)
    }

    @Test
    @TestCompactWidth
    fun backpackNavHost_verifyBackNavigationNotShownOnStoryScreen() {
        navigateToStoryScreen()
        assertNoBackButton()
    }

    override fun navigateToCharacterScreen() {
        composeTestRule.onNodeWithStringId(R.string.character_screen_title).performClick()
    }

    override fun navigateToInventoryScreen() {
        composeTestRule.onNodeWithStringId(R.string.inventory_screen_title).performClick()
    }

    override fun navigateToResourcesScreen() {
        composeTestRule.onNodeWithStringId(R.string.resources_screen_title).performClick()
    }

    override fun navigateToStoryScreen() {
        composeTestRule.onNodeWithStringId(R.string.story_screen_title).performClick()
    }
}
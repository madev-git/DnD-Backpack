package com.mad.dndbackpack

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.performClick
import com.mad.dndbackpack.ui.DnDBackPackScreen
import com.mad.dndbackpack.ui.DnDBackpackUiState
import org.junit.Before
import org.junit.Test

class DnDBackpackExpandedScreenNavigationTest : DnDBackpackBaseNavigationTest() {

    @Before
    fun setupBackpackCompactNavHost() {
        composeTestRule.setContent {
            setupBackpackNavHost(LocalContext.current)
            DnDBackPackScreen(
                windowSize = WindowWidthSizeClass.Companion.Expanded,
                dnDBackpackUiState = DnDBackpackUiState(),
                navController = navController
            )
        }
    }

    @Test
    @TestExpandedWidth
    fun backpackNavHost_verifyUsingPermanentNavigationDrawer() {
        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_drawer
        ).assertExists()
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
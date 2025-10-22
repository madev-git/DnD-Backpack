package com.mad.dndbackpack

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.performClick
import com.mad.dndbackpack.ui.DnDBackPackScreen
import com.mad.dndbackpack.ui.DnDBackpackUiState
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
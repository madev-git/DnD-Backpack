package com.mad.dndbackpack

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.mad.dndbackpack.ui.utils.DnDBackpackScreenEnum
import org.junit.Rule
import org.junit.Test

abstract class DnDBackpackBaseNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    lateinit var navController: TestNavHostController

    fun setupBackpackNavHost(context: Context) {
        navController = TestNavHostController(context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
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
    fun backpackNavHost_clickOnInventoryItem_navigateToInventoryScreen() {
        navigateToInventoryScreen()
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Inventory.name)
    }

    @Test
    fun backpackNavHost_verifyBackNavigationNotShownOnInventoryScreen() {
        navigateToInventoryScreen()
        assertNoBackButton()
    }

    @Test
    fun backpackNavHost_clickOnCharacterItem_navigateToCharacterScreen() {
        navigateToInventoryScreen()
        navigateToCharacterScreen()
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Character.name)
    }

    @Test
    fun backpackNavHost_clickOnResourcesItem_navigateToResourcesScreen() {
        navigateToResourcesScreen()
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Resources.name)
    }

    @Test
    fun backpackNavHost_verifyBackNavigationNotShownOnResourcesScreen() {
        navigateToResourcesScreen()
        assertNoBackButton()
    }

    @Test
    fun backpackNavHost_clickOnStoryItem_navigateToStoryScreen() {
        navigateToStoryScreen()
        navController.assertCurrentRouteName(DnDBackpackScreenEnum.Story.name)
    }

    @Test
    fun backpackNavHost_verifyBackNavigationNotShownOnStoryScreen() {
        navigateToStoryScreen()
        assertNoBackButton()
    }

    fun assertNoBackButton() {
        composeTestRule.onNodeWithContentDescriptionForStringId(R.string.back_button)
            .assertDoesNotExist()
    }

    abstract fun navigateToCharacterScreen()
    abstract fun navigateToInventoryScreen()
    abstract fun navigateToResourcesScreen()
    abstract fun navigateToStoryScreen()
}
package com.mad.dndbackpack

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Rule

abstract class DnDBackpackBaseNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    lateinit var navController: TestNavHostController

    fun setupBackpackNavHost(context: Context) {
        navController = TestNavHostController(context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
        }
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
package com.mad.tabletopbackpack.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mad.tabletopbackpack.R
import com.mad.tabletopbackpack.ui.BackpackMainScreens
import com.mad.tabletopbackpack.ui.theme.TabletopBackpackTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackpackAppBar(
    currentScreen: BackpackMainScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(text = stringResource(currentScreen.title))
        },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_back_24px),
                        contentDescription = stringResource(R.string.back_button),
                    )
                }
            }
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun FlowFiAppBarNoBackNavigationPreview() {
    TabletopBackpackTheme {
        Surface {
            BackpackAppBar(
                currentScreen = BackpackMainScreens.Character,
                canNavigateBack = false,
                navigateUp = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FlowFiAppBarWithBackNavigationPreview() {
    TabletopBackpackTheme {
        Surface {
            BackpackAppBar(
                currentScreen = BackpackMainScreens.Character,
                canNavigateBack = true,
                navigateUp = {},
            )
        }
    }
}

package com.mad.tabletopbackpack.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mad.tabletopbackpack.ui.BackpackMainScreens
import com.mad.tabletopbackpack.ui.theme.TabletopBackpackTheme

@Composable
fun BackpackBottomNavigationBar(
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val startDestination = BackpackMainScreens.Character
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets, modifier = modifier) {
        BackpackMainScreens.entries.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = selectedDestination == index,
                onClick = {
                    onClick(destination.name)
                    selectedDestination = index
                },
                icon = {
                    Icon(
                        painter = painterResource(destination.icon),
                        contentDescription = stringResource(destination.contentDescription),
                    )
                },
                label = { Text(stringResource(destination.title)) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FlowFiAppBarPreview() {
    TabletopBackpackTheme {
        Surface {
            BackpackBottomNavigationBar(
                onClick = {},
            )
        }
    }
}

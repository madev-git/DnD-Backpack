package com.mad.dndbackpack.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mad.dndbackpack.ui.theme.DnDBackpackTheme
import com.mad.dndbackpack.ui.utils.DnDBackpackScreenEnum

@Composable
fun DnDBackpackNavigationRail(
    onClick: (String) -> Unit,
    containerColor: Color,
    modifier: Modifier = Modifier,
) {
    val startDestination = DnDBackpackScreenEnum.Character
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    NavigationRail(containerColor = containerColor, modifier = modifier) {
        DnDBackpackScreenEnum.entries.forEachIndexed { index, destination ->
            NavigationRailItem(
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
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DnDBackpackNavigationRailPreview() {
    DnDBackpackTheme {
        Surface {
            DnDBackpackNavigationRail(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.inverseOnSurface,
            )
        }
    }
}

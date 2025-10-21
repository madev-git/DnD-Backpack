package com.mad.dndbackpack.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mad.dndbackpack.R
import com.mad.dndbackpack.ui.theme.DnDBackpackTheme
import com.mad.dndbackpack.ui.utils.DnDBackpackScreenEnum

/**
 * Will see some changes in near future
 */

@Composable
fun DnDBackpackNavigationDrawerContent(
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val startDestination = DnDBackpackScreenEnum.Character
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Column(modifier = modifier) {
        DnDBackpackNavigationDrawerHeader(
            modifier =
                Modifier
                    .fillMaxWidth(),
            // .padding(dimensionResource(R.dimen.profile_image_padding)),
        )
        DnDBackpackScreenEnum.entries.forEachIndexed { index, destination ->
            NavigationDrawerItem(
                selected = selectedDestination == index,
                label = {
                    Text(
                        text = stringResource(destination.title),
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.drawer_padding_header)),
                    )
                },
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
                colors =
                    NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                    ),
            )
        }
    }
}

@Composable
private fun DnDBackpackNavigationDrawerHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        /*ReplyLogo(modifier = Modifier.size(dimensionResource(R.dimen.reply_logo_size)))
        ReplyProfileImage(
            drawableResource = LocalAccountsDataProvider.defaultAccount.avatar,
            description = stringResource(id = R.string.profile),
            modifier = Modifier.size(dimensionResource(R.dimen.profile_image_size))
        )*/
    }
}

@Preview(showBackground = true)
@Composable
private fun DnDBackpackNavigationDrawerContentPreview() {
    DnDBackpackTheme {
        Surface {
            DnDBackpackNavigationDrawerContent(
                onClick = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DnDBackpackNavigationDrawerHeaderPreview() {
    DnDBackpackTheme {
        Surface {
            DnDBackpackNavigationDrawerHeader()
        }
    }
}

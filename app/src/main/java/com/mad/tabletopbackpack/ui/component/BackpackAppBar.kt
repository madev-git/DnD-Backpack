package com.mad.tabletopbackpack.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mad.tabletopbackpack.R
import com.mad.tabletopbackpack.ui.theme.TabletopBackpackTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackpackAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun FlowFiAppBarPreview() {
    TabletopBackpackTheme {
        Surface {
            BackpackAppBar()
        }
    }
}

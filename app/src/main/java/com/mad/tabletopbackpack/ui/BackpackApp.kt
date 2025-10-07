package com.mad.tabletopbackpack.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mad.tabletopbackpack.ui.component.BackpackAppBar
import com.mad.tabletopbackpack.ui.theme.TabletopBackpackTheme

@Composable
fun BackpackApp(
    viewModel: BackpackViewModel = viewModel(factory = BackpackViewModel.Factory),
    windowSize: WindowWidthSizeClass,
) {
    val backpackUiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            BackpackAppBar()
        },
    ) { innerPadding ->
        BackpackMainScreen(
            backpackUiState = backpackUiState,
            modifier =
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FlowFiAppCompactPreview() {
    TabletopBackpackTheme {
        Surface {
            BackpackApp(windowSize = WindowWidthSizeClass.Compact)
        }
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
private fun FlowFiAppMediumPreview() {
    TabletopBackpackTheme {
        Surface {
            BackpackApp(windowSize = WindowWidthSizeClass.Medium)
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
private fun FlowFiAppExpandedPreview() {
    TabletopBackpackTheme {
        Surface {
            BackpackApp(windowSize = WindowWidthSizeClass.Expanded)
        }
    }
}

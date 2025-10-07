package com.mad.tabletopbackpack.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mad.tabletopbackpack.BackpackApplication
import com.mad.tabletopbackpack.data.repository.PlayerCharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BackpackViewModel(
    private val playerCharacterRepository: PlayerCharacterRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(BackpackUiState())
    val uiState: StateFlow<BackpackUiState> = _uiState.asStateFlow()

    init {
        initializeUiState()
    }

    private fun initializeUiState() {
        _uiState.value =
            BackpackUiState(
                state = "Hello Backpack",
            )
    }

    /**
     * Factory for [BackpackViewModel] that takes [PlayerCharacterRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val playerCharacterRepository =
                        (this[APPLICATION_KEY] as BackpackApplication).container.playerCharacterRepository
                    BackpackViewModel(
                        playerCharacterRepository = playerCharacterRepository,
                    )
                }
            }
    }
}

package com.mad.dndbackpack.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mad.dndbackpack.DnDBackpackApplication
import com.mad.dndbackpack.data.repository.PlayerCharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DnDBackpackViewModel(
    private val playerCharacterRepository: PlayerCharacterRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(DnDBackpackUiState())
    val uiState: StateFlow<DnDBackpackUiState> = _uiState.asStateFlow()

    init {
        initializeUiState()
    }

    private fun initializeUiState() {
        _uiState.value =
            DnDBackpackUiState(
                state = "Hello Backpack",
            )
    }

    /**
     * Factory for [DnDBackpackViewModel] that takes [PlayerCharacterRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val playerCharacterRepository =
                        (this[APPLICATION_KEY] as DnDBackpackApplication).container.playerCharacterRepository
                    DnDBackpackViewModel(
                        playerCharacterRepository = playerCharacterRepository,
                    )
                }
            }
    }
}

package com.mad.dndbackpack.data.repository

import com.mad.dndbackpack.data.entity.PlayerCharacter
import kotlinx.coroutines.flow.Flow

interface PlayerCharacterRepository {
    suspend fun insert(playerCharacter: PlayerCharacter)

    suspend fun update(playerCharacter: PlayerCharacter)

    suspend fun delete(playerCharacter: PlayerCharacter)

    fun getPlayerCharacterStream(id: Int): Flow<PlayerCharacter>

    fun getAllPlayerCharactersStream(): Flow<List<PlayerCharacter>>
}

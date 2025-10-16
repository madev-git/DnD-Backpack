package com.mad.dndbackpack.data.repository

import com.mad.dndbackpack.data.entity.CharacterClass
import kotlinx.coroutines.flow.Flow

interface CharacterClassRepository {
    suspend fun insert(characterClass: CharacterClass)

    suspend fun update(characterClass: CharacterClass)

    suspend fun delete(characterClass: CharacterClass)

    fun getCharacterClassStream(id: Int): Flow<CharacterClass>

    fun getAllCharacterClassesStream(): Flow<List<CharacterClass>>
}

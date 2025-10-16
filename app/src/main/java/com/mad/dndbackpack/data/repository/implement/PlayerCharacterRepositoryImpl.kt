package com.mad.dndbackpack.data.repository.implement

import com.mad.dndbackpack.data.dao.PlayerCharacterDao
import com.mad.dndbackpack.data.entity.PlayerCharacter
import com.mad.dndbackpack.data.repository.PlayerCharacterRepository
import kotlinx.coroutines.flow.Flow

class PlayerCharacterRepositoryImpl(
    private val playerCharacterDao: PlayerCharacterDao,
) : PlayerCharacterRepository {
    override suspend fun insert(playerCharacter: PlayerCharacter) = playerCharacterDao.insert(playerCharacter)

    override suspend fun update(playerCharacter: PlayerCharacter) = playerCharacterDao.update(playerCharacter)

    override suspend fun delete(playerCharacter: PlayerCharacter) = playerCharacterDao.delete(playerCharacter)

    override fun getPlayerCharacterStream(id: Int): Flow<PlayerCharacter> = playerCharacterDao.getPlayerCharacter(id)

    override fun getAllPlayerCharactersStream(): Flow<List<PlayerCharacter>> = playerCharacterDao.getAllPlayerCharacters()
}

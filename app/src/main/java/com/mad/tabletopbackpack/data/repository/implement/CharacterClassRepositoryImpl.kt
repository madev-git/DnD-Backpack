package com.mad.tabletopbackpack.data.repository.implement

import com.mad.tabletopbackpack.data.dao.CharacterClassDao
import com.mad.tabletopbackpack.data.entity.CharacterClass
import com.mad.tabletopbackpack.data.repository.CharacterClassRepository
import kotlinx.coroutines.flow.Flow

class CharacterClassRepositoryImpl(
    private val characterClassDao: CharacterClassDao,
) : CharacterClassRepository {
    override suspend fun insert(characterClass: CharacterClass) = characterClassDao.insert(characterClass)

    override suspend fun update(characterClass: CharacterClass) = characterClassDao.update(characterClass)

    override suspend fun delete(characterClass: CharacterClass) = characterClassDao.delete(characterClass)

    override fun getCharacterClassStream(id: Int): Flow<CharacterClass> = characterClassDao.getCharacterClass(id)

    override fun getAllCharacterClassesStream(): Flow<List<CharacterClass>> = characterClassDao.getAllCharacterClasses()
}

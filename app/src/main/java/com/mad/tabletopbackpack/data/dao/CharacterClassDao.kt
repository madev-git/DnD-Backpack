package com.mad.tabletopbackpack.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mad.tabletopbackpack.data.entity.CharacterClass
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterClassDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(characterClass: CharacterClass)

    @Update
    suspend fun update(characterClass: CharacterClass)

    @Delete
    suspend fun delete(characterClass: CharacterClass)

    @Query("SELECT * from characterClass WHERE id = :id")
    fun getCharacterClass(id: Int): Flow<CharacterClass>

    @Query("SELECT * FROM characterClass")
    fun getAllCharacterClasses(): Flow<List<CharacterClass>>
}

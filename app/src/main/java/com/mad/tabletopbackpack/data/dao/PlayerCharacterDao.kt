package com.mad.tabletopbackpack.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mad.tabletopbackpack.data.entity.PlayerCharacter
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerCharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(character: PlayerCharacter)

    @Update
    suspend fun update(character: PlayerCharacter)

    @Delete
    suspend fun delete(character: PlayerCharacter)

    @Query("SELECT * from playerCharacter WHERE id = :id")
    fun getPlayerCharacter(id: Int): Flow<PlayerCharacter>

    @Query("SELECT * FROM playerCharacter")
    fun getAllPlayerCharacters(): Flow<List<PlayerCharacter>>
}

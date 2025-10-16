package com.mad.dndbackpack.data

import android.content.Context
import com.mad.dndbackpack.data.repository.CharacterClassRepository
import com.mad.dndbackpack.data.repository.ItemRepository
import com.mad.dndbackpack.data.repository.PlayerCharacterRepository
import com.mad.dndbackpack.data.repository.implement.CharacterClassRepositoryImpl
import com.mad.dndbackpack.data.repository.implement.ItemRepositoryImpl
import com.mad.dndbackpack.data.repository.implement.PlayerCharacterRepositoryImpl

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val itemRepository: ItemRepository
    val characterClassRepository: CharacterClassRepository
    val playerCharacterRepository: PlayerCharacterRepository
}

/**
 * [AppContainer] implementation
 */
class AppDataContainer(
    private val context: Context,
) : AppContainer {
    override val itemRepository: ItemRepository by lazy {
        ItemRepositoryImpl(DnDBackpackDatabase.getDatabase(context).itemDao())
    }

    override val characterClassRepository: CharacterClassRepository by lazy {
        CharacterClassRepositoryImpl(DnDBackpackDatabase.getDatabase(context).characterClassDao())
    }
    override val playerCharacterRepository: PlayerCharacterRepository by lazy {
        PlayerCharacterRepositoryImpl(DnDBackpackDatabase.getDatabase(context).playerCharacterDao())
    }
}

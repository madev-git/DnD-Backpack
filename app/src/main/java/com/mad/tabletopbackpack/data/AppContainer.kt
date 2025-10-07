package com.mad.tabletopbackpack.data

import android.content.Context
import com.mad.tabletopbackpack.data.repository.CharacterClassRepository
import com.mad.tabletopbackpack.data.repository.ItemRepository
import com.mad.tabletopbackpack.data.repository.PlayerCharacterRepository
import com.mad.tabletopbackpack.data.repository.implement.CharacterClassRepositoryImpl
import com.mad.tabletopbackpack.data.repository.implement.ItemRepositoryImpl
import com.mad.tabletopbackpack.data.repository.implement.PlayerCharacterRepositoryImpl

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
        ItemRepositoryImpl(BackpackDatabase.getDatabase(context).itemDao())
    }

    override val characterClassRepository: CharacterClassRepository by lazy {
        CharacterClassRepositoryImpl(BackpackDatabase.getDatabase(context).characterClassDao())
    }
    override val playerCharacterRepository: PlayerCharacterRepository by lazy {
        PlayerCharacterRepositoryImpl(BackpackDatabase.getDatabase(context).playerCharacterDao())
    }
}

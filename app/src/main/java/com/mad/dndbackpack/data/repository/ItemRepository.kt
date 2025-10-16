package com.mad.dndbackpack.data.repository

import com.mad.dndbackpack.data.entity.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun insert(item: Item)

    suspend fun update(item: Item)

    suspend fun delete(item: Item)

    fun getItemStream(id: Int): Flow<Item>

    fun getAllItemsStream(): Flow<List<Item>>
}

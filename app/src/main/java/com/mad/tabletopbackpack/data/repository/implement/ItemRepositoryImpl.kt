package com.mad.tabletopbackpack.data.repository.implement

import com.mad.tabletopbackpack.data.dao.ItemDao
import com.mad.tabletopbackpack.data.entity.Item
import com.mad.tabletopbackpack.data.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class ItemRepositoryImpl(
    private val itemDao: ItemDao,
) : ItemRepository {
    override suspend fun insert(item: Item) = itemDao.insert(item)

    override suspend fun update(item: Item) = itemDao.update(item)

    override suspend fun delete(item: Item) = itemDao.delete(item)

    override fun getItemStream(id: Int): Flow<Item> = itemDao.getItem(id)

    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()
}

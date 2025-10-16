package com.mad.dndbackpack.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mad.dndbackpack.data.dao.CharacterClassDao
import com.mad.dndbackpack.data.dao.ItemDao
import com.mad.dndbackpack.data.dao.PlayerCharacterDao
import com.mad.dndbackpack.data.entity.CharacterClass
import com.mad.dndbackpack.data.entity.Item
import com.mad.dndbackpack.data.entity.PlayerCharacter

@Database(
    entities = [
        PlayerCharacter::class,
        CharacterClass::class,
        Item::class,
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class DnDBackpackDatabase : RoomDatabase() {
    abstract fun playerCharacterDao(): PlayerCharacterDao

    abstract fun characterClassDao(): CharacterClassDao

    abstract fun itemDao(): ItemDao

    /**
     * The value of a volatile variable is never cached, and all reads and writes are to and
     * from the main memory. These features help ensure the value of instance is always
     * up to date and is the same for all execution threads. It means that changes made
     * by one thread to instance are immediately visible to all other threads
     */
    companion object {
        @Volatile
        private var instance: DnDBackpackDatabase? = null

        fun getDatabase(context: Context): DnDBackpackDatabase =
            instance ?: synchronized(this) {
                Room
                    .databaseBuilder(context, DnDBackpackDatabase::class.java, "backpack_database")
                    .fallbackToDestructiveMigration(true)
                    .build()
                    .also { instance = it }
            }
    }
}

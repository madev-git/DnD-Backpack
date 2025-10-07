package com.mad.tabletopbackpack.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mad.tabletopbackpack.data.entity.CharacterClass
import com.mad.tabletopbackpack.data.entity.Item

class Converters {
    @TypeConverter
    fun fromItemList(value: List<Item>?): String? = value?.let { Gson().toJson(it) }

    @TypeConverter
    fun toItemList(value: String?): List<Item>? {
        val listType = object : TypeToken<List<Item>>() {}.type
        return value?.let { Gson().fromJson(it, listType) }
    }

    @TypeConverter
    fun fromCharacterClassMutableSet(value: MutableSet<CharacterClass>?): String? = value?.let { Gson().toJson(it) }

    @TypeConverter
    fun toCharacterClassMutableSet(value: String?): MutableSet<CharacterClass>? {
        val listType = object : TypeToken<MutableSet<CharacterClass>>() {}.type
        return value?.let { Gson().fromJson(it, listType) }
    }
}

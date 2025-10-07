package com.mad.tabletopbackpack.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayerCharacter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val species: String,
    val characterClass: MutableSet<CharacterClass> = mutableSetOf(),
    val background: String,
    val level: Int,
    val maxHealthPoints: Int,
    val currentHealthPoints: Int,
    val temporaryHealthPoints: Int,
    val inventory: List<Item> = listOf(),
    val armorCategory: String,
    val initiative: String,
    val speed: String,
    val feats: String,
    val proficiencyBonus: String,
    val strengthValue: Int,
    val dexterityValue: Int,
    val constitutionValue: Int,
    val intelligenceValue: Int,
    val wisdomValue: Int,
    val charismaValue: Int,
)

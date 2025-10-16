package com.mad.dndbackpack.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterClass(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hitPointDie: String,
    val level: Int,
    val isOriginClass: Boolean,
    val subClass: String,
)

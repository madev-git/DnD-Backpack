package com.mad.dndbackpack.ui.utils

import com.mad.dndbackpack.R

enum class DnDBackpackScreenEnum(
    val title: Int,
    val icon: Int,
    val contentDescription: Int,
) {
    Character(
        R.string.character_screen_title,
        R.drawable.person_24px,
        R.string.character_screen_title,
    ),
    Inventory(
        R.string.inventory_screen_title,
        R.drawable.backpack_24px,
        R.string.inventory_screen_title,
    ),
    Resources(
        R.string.resources_screen_title,
        R.drawable.checkbook_24px,
        R.string.resources_screen_title,
    ),
    Story(
        R.string.story_screen_title,
        R.drawable.edit_document_24px,
        R.string.story_screen_title,
    ),
}

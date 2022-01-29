package com.task.ui.base.listeners

import com.task.data.dto.recipes.RecipesItem


interface RecyclerItemListener {
    fun onItemSelected(recipe : RecipesItem)
}

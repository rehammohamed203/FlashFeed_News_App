package com.reham11203.flashfeed.ui.home.fragments.categories

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.reham11203.flashfeed.R

data class Category(
    val id: String,
    @StringRes
    val title: Int,
    @DrawableRes
    val image: Int
) {
    //
    companion object {
        fun getCategories(): List<Category> = listOf(
            Category(
                id = "general",
                title = R.string.general,
                image = R.drawable.general
            ),
            Category(
                id = "business",
                title = R.string.business,
                image = R.drawable.business
            ),
            Category(
                id = "sports",
                title = R.string.sports,
                image = R.drawable.sports
            ),
            Category(
                id = "technology",
                title = R.string.technology,
                image = R.drawable.technology
            ),
            Category(
                id = "entertainment",
                title = R.string.entertainment,
                image = R.drawable.entertainment
            ),
            Category(
                id = "health",
                title = R.string.health,
                image = R.drawable.health
            ),
            Category(
                id = "science",
                title = R.string.science,
                image = R.drawable.science
            )
        )
    }
}
package com.reham11203.flashfeed.ui.home.fragments.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reham11203.flashfeed.databinding.ItemCategoryBinding

class CategoriesAdapter(
    val categories: List<Category> = Category.getCategories(),
    val onCategoryClick: ((category: Category) -> Unit)
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(categories[position])
        holder.itemBinding.categoryImg.setOnClickListener {
            onCategoryClick(categories[position])
        }
    }

    override fun getItemCount(): Int = categories.size

    class ViewHolder(val itemBinding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(category: Category) {
            itemBinding.categoryImg.setImageResource(category.image)
        }
    }
}
package com.bhushan.tastebuds.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bhushan.tastebuds.R
import com.bhushan.tastebuds.architecturalComponents.model.recipeFormatted.FormattedMealsList
import com.bhushan.tastebuds.databinding.ItemInnerRecipeListBinding
import com.bumptech.glide.Glide

class InnerIngredientAdapter(): ListAdapter<FormattedMealsList.Meal.Ingredient, InnerIngredientAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val binding: ItemInnerRecipeListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(ingredient: FormattedMealsList.Meal.Ingredient){
            binding.ingredientName.text = ingredient.strIngredient
            Glide.with(itemView.context)
                .load(ingredient.image)
                .placeholder(R.drawable.baseline_fastfood_24)
                .error(R.drawable.baseline_error_outline_24)
                .into(binding.ingredientImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemInnerRecipeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<FormattedMealsList.Meal.Ingredient>() {
        override fun areItemsTheSame(oldItem: FormattedMealsList.Meal.Ingredient, newItem: FormattedMealsList.Meal.Ingredient): Boolean {
            return oldItem.strIngredient == newItem.strIngredient
        }

        override fun areContentsTheSame(oldItem: FormattedMealsList.Meal.Ingredient, newItem: FormattedMealsList.Meal.Ingredient): Boolean {
            return oldItem == newItem
        }
    }
}
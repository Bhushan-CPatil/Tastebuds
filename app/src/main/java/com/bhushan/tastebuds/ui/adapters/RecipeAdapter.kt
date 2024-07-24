package com.bhushan.tastebuds.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bhushan.tastebuds.R
import com.bhushan.tastebuds.architecturalComponents.model.recipeFormatted.FormattedMealsList
import com.bhushan.tastebuds.databinding.ItemRecipeListBinding
import com.bumptech.glide.Glide

class RecipeAdapter(private val onAction: (FormattedMealsList.Meal, Action) -> Unit) : ListAdapter<FormattedMealsList.Meal, RecipeAdapter.ViewHolder>(DiffCallback()) {

    enum class Action {
        VIEW
    }

    class ViewHolder(private val binding: ItemRecipeListBinding) : RecyclerView.ViewHolder(binding.root) {
        private val ingredientAdapter = InnerIngredientAdapter()
        fun bind(meal: FormattedMealsList.Meal, onAction: (FormattedMealsList.Meal, Action) -> Unit) {
            binding.title.text = meal.strMeal
            binding.description.text = meal.strInstructions
            Glide.with(itemView.context)
                .load(meal.strMealThumb)
                .placeholder(R.drawable.baseline_fastfood_24)
                .error(R.drawable.baseline_error_outline_24)
                .into(binding.thumbnailImage)

            binding.ingredientList.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            binding.ingredientList.adapter = ingredientAdapter
            ingredientAdapter.submitList(meal.ingredients)

            itemView.setOnClickListener {
                onAction(meal, Action.VIEW)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecipeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onAction)
    }

    class DiffCallback : DiffUtil.ItemCallback<FormattedMealsList.Meal>() {
        override fun areItemsTheSame(oldItem: FormattedMealsList.Meal, newItem: FormattedMealsList.Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: FormattedMealsList.Meal, newItem: FormattedMealsList.Meal): Boolean {
            return oldItem == newItem
        }
    }
}
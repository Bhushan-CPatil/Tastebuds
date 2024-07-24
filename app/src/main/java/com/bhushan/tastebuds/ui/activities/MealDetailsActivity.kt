package com.bhushan.tastebuds.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhushan.tastebuds.R
import com.bhushan.tastebuds.architecturalComponents.model.recipeFormatted.FormattedMealsList
import com.bhushan.tastebuds.databinding.ActivityMealDetailsBinding
import com.bhushan.tastebuds.ui.adapters.IngredientAdapter
import com.bumptech.glide.Glide
import kotlin.random.Random


class MealDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealDetailsBinding
    private lateinit var ingredientAdapter: IngredientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        initUI()
    }

    private fun initUI() {

        binding.close.setOnClickListener {
            finish()
        }

        ingredientAdapter = IngredientAdapter()
        binding.ingredientList.apply {
            layoutManager = LinearLayoutManager(this@MealDetailsActivity)
            adapter = ingredientAdapter
        }

        val meal: FormattedMealsList.Meal? = intent.getParcelableExtra("mealItem")
        meal?.let {
            binding.title.text = it.strMeal
            binding.description.text = it.strInstructions
            binding.time.text = "${Random.nextInt(15, 60)} Min"
            binding.itemcount.text = "${it.ingredients.size} Items"
            val url = it.strYoutube
            binding.openlink.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(url)
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
            Glide.with(applicationContext)
                .load(it.strMealThumb)
                .placeholder(R.drawable.baseline_fastfood_24)
                .error(R.drawable.baseline_error_outline_24)
                .into(binding.thumbnailImage)

            ingredientAdapter.submitList(it.ingredients)
        }
    }
}
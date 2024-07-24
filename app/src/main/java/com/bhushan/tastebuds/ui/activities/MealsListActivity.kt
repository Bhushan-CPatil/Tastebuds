package com.bhushan.tastebuds.ui.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhushan.tastebuds.R
import com.bhushan.tastebuds.architecturalComponents.helper.Status
import com.bhushan.tastebuds.architecturalComponents.viewmodel.MealViewModel
import com.bhushan.tastebuds.databinding.ActivityMealsListBinding
import com.bhushan.tastebuds.ui.adapters.RecipeAdapter
import com.bhushan.tastebuds.utility.Progress
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MealsListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealsListBinding
    private val viewModel: MealViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeAdapter
    val progress: Progress by lazy {
        Progress(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMealsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {

        recipeAdapter = RecipeAdapter{ meal, action ->
            when (action) {
                RecipeAdapter.Action.VIEW -> {
                    val intent = Intent(this, MealDetailsActivity::class.java)
                    intent.putExtra("mealItem", meal)
                    startActivity(intent)
                }
            }
        }

        binding.recipeList.apply {
            layoutManager = LinearLayoutManager(this@MealsListActivity)
            adapter = recipeAdapter
        }

        binding.recipeList.layoutManager = LinearLayoutManager(this)

        observe()
        viewModel.MealList()
    }

    private fun observe() {
        viewModel.formattedMealList.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    progress.show()
                }
                Status.SUCCESS -> {
                    progress.dismiss()
                    if (it.data?.meals?.size!! > 0) {
                        recipeAdapter.submitList(it.data.meals)
                    }
                }
                Status.ERROR -> {
                    progress.dismiss()
                    it.message?.let { it1 ->
                        Log.d("TAG", "observe: ${it.message}")
                    }
                }
            }
        }
    }
}
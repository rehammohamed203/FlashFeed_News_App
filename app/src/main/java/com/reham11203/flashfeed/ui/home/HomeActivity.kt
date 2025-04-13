package com.reham11203.flashfeed.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.reham11203.flashfeed.R
import com.reham11203.flashfeed.databinding.ActivityHomeBinding
import com.reham11203.flashfeed.ui.home.fragments.categories.CategoriesFragment
import com.reham11203.flashfeed.ui.home.fragments.categories.Category
import com.reham11203.flashfeed.ui.home.fragments.news.NewsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appBarHome.toolBar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }
        showCategoriesFragment(
            CategoriesFragment.getInstance(
                onCategoryClickCallback = ::onCategoryClick
            )
        )
        initNavView()

    }

    private fun initNavView() {
        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    showCategoriesFragment(
                        CategoriesFragment.getInstance(
                            onCategoryClickCallback = ::onCategoryClick
                        )
                    )
                }
            }
            binding.drawerLayout.close()
            return@setNavigationItemSelectedListener true

        }
    }

    private fun onCategoryClick(category: Category) {
        showNewsFragment(category)

    }

    private fun showCategoriesFragment(fragment: CategoriesFragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.news_fragment_container, fragment)
            .commit()
    }

    private fun showNewsFragment(category: Category) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.news_fragment_container, NewsFragment.getInstance(category))
            .addToBackStack(null)
            .commit()
    }

}
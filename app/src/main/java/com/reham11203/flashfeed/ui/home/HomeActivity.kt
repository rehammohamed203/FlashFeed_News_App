package com.reham11203.flashfeed.ui.home

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.reham11203.flashfeed.R
import com.reham11203.flashfeed.databinding.ActivityHomeBinding
import com.reham11203.flashfeed.ui.home.fragments.categories.CategoriesFragmentDirections
import com.reham11203.flashfeed.ui.home.fragments.categories.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        setSupportActionBar(binding.appBarHome.toolBar)
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        binding.navView.setupWithNavController(navController)

        setupActionBarWithNavController(navController, appBarConfiguration)
        supportActionBar?.title = ""

        binding.appBarHome.toolBar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        initNavView()

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) or super.onSupportNavigateUp()
    }

    fun setCustomToolbarTitle(title: String) {
        val toolbarTitle = findViewById<TextView>(R.id.app_bar_title)
        toolbarTitle?.text = title
    }

    private fun initNavView() {
        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.categoriesFragment -> {
                    navController.navigate(R.id.categoriesFragment)
                }

                R.id.languagesFragment -> {
                    navController.navigate(R.id.languagesFragment)
                }

                R.id.themesFragment -> {
                    navController.navigate(R.id.themesFragment)
                }
            }
            binding.drawerLayout.close()
            return@setNavigationItemSelectedListener true

        }
    }

    fun onCategoryClick(category: Category) {
        val action = CategoriesFragmentDirections
            .actionCategoriesFragmentToNewsFragment(category)
        navController.navigate(action)
    }



}
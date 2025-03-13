package com.reham11203.flashfeed.ui.home.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reham11203.flashfeed.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {
    var _binding: FragmentCategoriesBinding? = null
    val binding get() = _binding!!
    val adapter = CategoriesAdapter(onCategoryClick = ::onCategoryClick)

    companion object {
        fun getInstance(onCategoryClickCallback: OnCategoryClickCallback): CategoriesFragment {
            val fragment = CategoriesFragment()
            fragment.onCategoryClickCallback = onCategoryClickCallback
            return fragment
        }
    }

    fun interface OnCategoryClickCallback {
        fun onCategoryClick(category: Category)

    }

    private var onCategoryClickCallback: OnCategoryClickCallback? = null
    private fun onCategoryClick(category: Category) {
        onCategoryClickCallback?.onCategoryClick(category)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }


    private fun initRecyclerView() {
        binding.categoriesRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
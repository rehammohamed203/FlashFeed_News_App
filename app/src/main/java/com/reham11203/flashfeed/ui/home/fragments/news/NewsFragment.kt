package com.reham11203.flashfeed.ui.home.fragments.news
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.reham11203.domain.model.News
import com.reham11203.domain.model.Source
import com.reham11203.flashfeed.common.ErrorState
import com.reham11203.flashfeed.databinding.FragmentNewsBinding
import com.reham11203.flashfeed.ui.home.fragments.categories.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    lateinit var category: Category
    val viewModel: NewsViewModel by viewModels<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun getInstance(category: Category): NewsFragment {
            val fragment = NewsFragment()
            fragment.category = category
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNewsRecyclerView()
        viewModel.loadSources(category.id)
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.sourcesLiveData.observe(viewLifecycleOwner) { sources ->
            bindTabLayout(sources)
        }
        viewModel.showErrorViewLiveData.observe(viewLifecycleOwner) { errorState ->
            showErrorView(errorState)
        }
        viewModel.showLoadingView.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading)
                showLoadingView()

        }
        viewModel.newsLiveData.observe(viewLifecycleOwner) { news ->
            showSuccessView()
            bindNewsList(news)

        }
    }

    private fun initNewsRecyclerView() {
        binding.newsRecyclerView.adapter = adapter
    }


    private fun bindTabLayout(sources: List<Source?>?) {
        showSuccessView()
        sources?.forEach { source ->
            val tab = binding.sourcesTabs.newTab()
            tab.text = source?.name
            tab.tag = source
            binding.sourcesTabs.addTab(tab)

        }
        binding.sourcesTabs.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source?
                source?.sourceId?.let {
                    viewModel.loadNews(it)
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source?
                source?.sourceId?.let {
                    viewModel.loadNews(it)
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })
        binding.sourcesTabs.getTabAt(0)?.select()

    }


    val adapter = NewsAdapter()

    private fun bindNewsList(newsList: List<News?>?) {
        adapter.changeData(newsList)
    }

    private fun showLoadingView() {
        binding.loadingView.isVisible = true
        binding.errorView.isVisible = false
    }

    private fun showSuccessView() {
        binding.loadingView.isVisible = false
        binding.errorView.isVisible = false
    }

    private fun showErrorView(errorState: ErrorState) {
        binding.loadingView.isVisible = false
        binding.errorView.isVisible = true
        binding.errorMessage.text = errorState.errorMessage
        binding.tryAgainBtn.setOnClickListener {
            errorState.onTryAgain?.invoke()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
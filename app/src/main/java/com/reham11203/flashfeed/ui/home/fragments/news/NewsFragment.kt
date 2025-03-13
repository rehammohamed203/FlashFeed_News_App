package com.reham11203.flashfeed.ui.home.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import com.reham11203.flashfeed.api.ApiManager
import com.reham11203.flashfeed.api.models.ErrorResponse
import com.reham11203.flashfeed.api.models.news.News
import com.reham11203.flashfeed.api.models.news.NewsResponse
import com.reham11203.flashfeed.api.models.sources.Source
import com.reham11203.flashfeed.api.models.sources.SourcesResponse
import com.reham11203.flashfeed.databinding.FragmentNewsBinding
import com.reham11203.flashfeed.ui.home.fragments.categories.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    lateinit var category: Category
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
        loadSources()
    }

    private fun initNewsRecyclerView() {
        binding.newsRecyclerView.adapter = adapter
    }

    private fun loadSources() {
        showLoadingView()
        ApiManager.getWebServices()
            .getSources(category.id)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onFailure(call: Call<SourcesResponse>, error: Throwable) {
                    showErrorView(error.localizedMessage ?: "Something went wrong",
                        onTryAgainClick = {
                            loadSources()
                        })
                }

                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    if (!response.isSuccessful) {
                        val errorResponse = Gson().fromJson(
                            response.errorBody()?.string(),
                            SourcesResponse::class.java
                        )
                        val message = errorResponse.message ?: "something went wrong"
                        showErrorView(message,
                            onTryAgainClick = {
                                loadSources()
                            })
                        return
                    }
                    bindTabLayout(response.body()?.sources)
                }
            })
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
                source?.id?.let {
                    loadNews(it)
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source?
                source?.id?.let {
                    loadNews(it)
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })
        binding.sourcesTabs.getTabAt(0)?.select()

    }

    private fun loadNews(sourceId: String) {
        showLoadingView()
        ApiManager.getWebServices()
            .getNews(sourceId)
            .enqueue(object : Callback<NewsResponse> {
                override fun onFailure(call: Call<NewsResponse>, throwable: Throwable) {
                    showErrorView(throwable.localizedMessage ?: "Something went wrong",
                        onTryAgainClick = {
                            loadNews(sourceId)
                        })
                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (!response.isSuccessful) {
                        val errorResponse = Gson().fromJson(
                            response.errorBody()?.string(),
                            ErrorResponse::class.java
                        )
                        val message = errorResponse.message ?: "something went wrong"
                        showErrorView(message,
                            onTryAgainClick = {
                                loadNews(sourceId)
                            })
                        return
                    }
                    showSuccessView()
                    bindNewsList(response.body()?.newsList)
                }
            })

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

    private fun showErrorView(errorMessage: String, onTryAgainClick: () -> Unit) {
        binding.loadingView.isVisible = false
        binding.errorView.isVisible = true
        binding.errorMessage.text = errorMessage
        binding.tryAgainBtn.setOnClickListener {
            onTryAgainClick.invoke()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.reham11203.flashfeed.ui.home.fragments.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.reham11203.flashfeed.api.ApiManager
import com.reham11203.flashfeed.api.models.ErrorResponse
import com.reham11203.flashfeed.api.models.news.News
import com.reham11203.flashfeed.api.models.news.NewsResponse
import com.reham11203.flashfeed.api.models.sources.Source
import com.reham11203.flashfeed.api.models.sources.SourcesResponse
import com.reham11203.flashfeed.common.ErrorState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    val showLoadingView = MutableLiveData<Boolean>(false)
    val showErrorViewLiveData = MutableLiveData<ErrorState>()
    val sourcesLiveData = MutableLiveData<List<Source?>?>()
    val newsLiveData = MutableLiveData<List<News?>?>()
    fun loadSources(categoryId: String) {
        showLoadingView.value = true
        ApiManager.getWebServices()
            .getSources(categoryId)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onFailure(call: Call<SourcesResponse>, error: Throwable) {
                    showErrorViewLiveData.value = ErrorState(
                        errorMessage = error.localizedMessage ?: "Something went wrong",
                        onTryAgain = {
                            loadSources(categoryId)
                        }
                    )
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

                        showErrorViewLiveData.value = ErrorState(
                            errorMessage = message,
                            onTryAgain = {
                                loadSources(categoryId)
                            }
                        )
                        return
                    }
                    sourcesLiveData.value = response.body()?.sources
                }
            })
    }

    fun loadNews(sourceId: String) {
        showLoadingView.value = true
        ApiManager.getWebServices()
            .getNews(sourceId)
            .enqueue(object : Callback<NewsResponse> {
                override fun onFailure(call: Call<NewsResponse>, throwable: Throwable) {

                    showErrorViewLiveData.value = ErrorState(
                        errorMessage = throwable.localizedMessage ?: "Something went wrong",
                        onTryAgain = {
                            loadNews(sourceId)
                        }
                    )
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

                        showErrorViewLiveData.value = ErrorState(
                            errorMessage = message,
                            onTryAgain = {
                                loadNews(sourceId)
                            }
                        )
                        return
                    }
                    newsLiveData.value = response.body()?.newsList
                }
            })

    }

}
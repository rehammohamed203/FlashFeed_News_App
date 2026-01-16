package com.reham11203.flashfeed.ui.home.fragments.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reham11203.domain.model.News
import com.reham11203.domain.model.Source
import com.reham11203.domain.usecases.GetNewsUseCase
import com.reham11203.domain.usecases.GetSourcesUseCase
import com.reham11203.flashfeed.common.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val getSourcesUseCase: GetSourcesUseCase
) : ViewModel() {

    val showLoadingView = MutableLiveData<Boolean>(false)
    val showErrorViewLiveData = MutableLiveData<ErrorState>()
    val sourcesLiveData = MutableLiveData<List<Source?>?>()
    val newsLiveData = MutableLiveData<List<News?>?>()
    fun loadSources(categoryId: String) {
        showLoadingView.value = true
        viewModelScope.launch {
            try {
                val sourcesList = getSourcesUseCase.getSources(categoryId)
                sourcesLiveData.value = sourcesList
            } catch (ex: Exception) {
                showErrorViewLiveData.value = ErrorState(
                    errorMessage = ex.localizedMessage ?: "Something went wrong",
                    onTryAgain = {
                        loadSources(categoryId)
                    }
                )
            }
        }

    }

    fun loadNews(sourceId: String) {
        showLoadingView.value = true
        viewModelScope.launch {
            try {
                val newsList = getNewsUseCase.invoke(sourceId)
                newsLiveData.value = newsList

            } catch (ex: Exception) {
                showErrorViewLiveData.value = ErrorState(
                    errorMessage = ex.localizedMessage ?: "Something went wrong",
                    onTryAgain = {
                        loadNews(sourceId)
                    }
                )
            }

        }

    }


}
package jt.projects.topstoriesapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jt.projects.topstoriesapp.interactors.StoryInteractor
import jt.projects.topstoriesapp.model.Story
import jt.projects.topstoriesapp.utils.LOG_TAG
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val interactor: StoryInteractor) : ViewModel() {

    private var job: Job? = null

    private val _resultRecycler = MutableStateFlow<List<Story>>(listOf())
    val resultRecycler get() = _resultRecycler.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading get() = _isLoading.asStateFlow()

    init {
        Log.d(LOG_TAG,"######")
        loadData()
    }


    fun loadData() {
        _isLoading.tryEmit(true)

        launchOrError(
            action = {
                val data = interactor.getStories()
                _resultRecycler.tryEmit(data)
                _isLoading.tryEmit(false)
            },
            error = {
                _resultRecycler.tryEmit(listOf())
                _isLoading.tryEmit(false)
            }
        )
    }

    private fun launchOrError(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        action: suspend () -> Unit,
        error: (Exception) -> Unit
    ) {
        job?.cancel()
        job = viewModelScope.launch(dispatcher) {
            try {
                action.invoke()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(LOG_TAG, "$e")
                error.invoke(e)
            }
        }
    }
}
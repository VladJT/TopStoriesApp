package jt.projects.topstoriesapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import jt.projects.topstoriesapp.interactors.StoryInteractor
import jt.projects.topstoriesapp.model.Story
import jt.projects.topstoriesapp.utils.LOG_TAG
import jt.projects.topstoriesapp.utils.createMutableSingleEventFlow
import jt.projects.topstoriesapp.utils.launchOrError
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(private val interactor: StoryInteractor) : ViewModel() {

    private var job: Job? = null

    private val _resultRecycler = MutableStateFlow<List<Story>>(listOf())
    val resultRecycler get() = _resultRecycler.asStateFlow()

    private val _itemClicked = createMutableSingleEventFlow<Story>()
    val itemClicked get() = _itemClicked.asSharedFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading get() = _isLoading.asStateFlow()

    init {
        loadData()
    }


    private fun loadData() {
        _isLoading.tryEmit(true)

        job?.cancel()
        job = launchOrError(
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

    fun onItemClicked(data: Story) {
        Log.d(LOG_TAG, "onItemClicked $data")
        _itemClicked.tryEmit(data)
    }

}
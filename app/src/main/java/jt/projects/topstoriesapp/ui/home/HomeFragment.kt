package jt.projects.topstoriesapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import jt.projects.topstoriesapp.databinding.FragmentHomeBinding
import jt.projects.topstoriesapp.ui.adapters.StoryAdapter
import jt.projects.topstoriesapp.utils.showSnackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModel()
    private val storyAdapter by lazy { StoryAdapter(viewModel::onItemClicked) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observeStoryData()
        observeLoadingVisible()
        observeItemClicked()
    }

    private fun initUi() {
        with(binding.rvStories) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = storyAdapter
        }
    }

    private fun observeStoryData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.resultRecycler.collect {
                    storyAdapter.setData(it)
                }
            }
        }
    }

    private fun observeItemClicked() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.itemClicked.collect {
                    showSnackbar(it.title)
                }
            }
        }
    }

    private fun observeLoadingVisible() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect {
                    binding.loadingFrameLayout.root.isVisible = it
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
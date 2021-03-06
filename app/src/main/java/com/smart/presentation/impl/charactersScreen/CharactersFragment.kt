package com.smart.presentation.impl.charactersScreen

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.smart.R
import com.smart.base.BaseFragment
import com.smart.databinding.CharactersFragmentBinding
import com.smart.presentation.api.CharacterViewModel

class CharactersFragment : BaseFragment<CharacterViewModel, CharactersFragmentBinding>() {

    private val adapter by lazy(LazyThreadSafetyMode.NONE) { CharactersAdapter { viewModel.loadMore() } }
    private lateinit var snackBar: Snackbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.apply {
            title = getString(R.string.app_name)
            setDisplayHomeAsUpEnabled(false)
        }
        initView(view)
        observeViewModel()
        if (adapter.currentList.size == 0) {
            viewModel.getCharacters(0, false)
        }
    }

    private fun initView(view: View) {
        binding.recycler.adapter = adapter
        binding.recycler.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (!rv.canScrollVertically(1) && !adapter.isLoaderVisible) {
                    viewModel.loadMore()
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) = Unit
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) = Unit
        })
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getCharacters(0, true)
        }
        snackBar = Snackbar.make(view, getText(R.string.load_error), Snackbar.LENGTH_LONG)
    }

    private fun observeViewModel() {
        viewModel.listCharacters.observe { adapter.submitList(it) }
        viewModel.showBottomLoader.observe { adapter.loading(it) }
        viewModel.showError.observe { snackBar.show() }
        viewModel.isRefreshing.observe { binding.swipeRefresh.isRefreshing = it }
        viewModel.isProgress.observe { binding.progressBar.isVisible = it }
    }
}
package com.smart.presentation.impl.charactersScreen

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

import com.smart.base.BaseFragment
import com.smart.databinding.CharactersFragmentBinding
import com.smart.presentation.api.CharacterViewModel
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class CharactersFragment : BaseFragment<CharacterViewModel, CharactersFragmentBinding>() {

    private val adapter by lazy(LazyThreadSafetyMode.NONE) { CharactersAdapter { viewModel.loadMore() } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.main.setOnClickListener {
            viewModel.openAdditional(0)
        }

        observeViewModel()
        initView()
        viewModel.getCharacters(0)
    }

    private fun initView() {
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
    }

    private fun observeViewModel() {
        viewModel.listCharacters.observe {
            adapter.submitList(it)
            Timber.d("sdsds, $it")
        }
        viewModel.showBottomLoader.observe { adapter.loading(it) }
    }
}
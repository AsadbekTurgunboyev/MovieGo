package com.example.moviego.ui.main.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviego.databinding.FragmentSearchBinding
import com.example.moviego.ui.main.search.adapter.SearchAdapter
import com.example.moviego.utils.ResourceState
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(), TextWatcher {

    lateinit var viewBinding: FragmentSearchBinding
    val searchViewModel: SearchViewModel by viewModel()
    lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchAdapter = SearchAdapter(listOf())
        viewBinding.recylerviewSearch.adapter = searchAdapter
        viewBinding.searchMovieEdittext.addTextChangedListener(this)

        searchViewModel.searchMovie.observe(viewLifecycleOwner) {
            when (it.state) {
                ResourceState.LOADING -> {}
                ResourceState.ERROR -> {}
                ResourceState.SUCCESS -> {
                    it.data?.results?.let { results ->
                        searchAdapter.list = results
                        searchAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        val queryText = s.toString()
        searchAdapter.setQueryText(queryText)
        searchAdapter.refreshItems()
        searchViewModel.getSearchMovie(s.toString())

    }
}
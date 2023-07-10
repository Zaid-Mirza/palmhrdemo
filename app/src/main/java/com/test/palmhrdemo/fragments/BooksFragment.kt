package com.test.palmhrdemo.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.palmhrdemo.R
import com.test.palmhrdemo.adapters.BooksAdapter
import com.test.palmhrdemo.databinding.FragmentBooksBinding
import com.test.palmhrdemo.models.FilterObject
import com.test.palmhrdemo.models.GeneralResponse
import com.test.palmhrdemo.models.Items
import com.test.palmhrdemo.models.Resource
import com.test.palmhrdemo.repositories.BooksRepository
import com.test.palmhrdemo.utils.AppEnums
import com.test.palmhrdemo.utils.Constants
import com.test.palmhrdemo.utils.TextUtil
import com.test.palmhrdemo.utils.ViewUtil.gone
import com.test.palmhrdemo.utils.ViewUtil.show
import com.test.palmhrdemo.viewmodels.BookViewModel
import com.test.palmhrdemo.viewmodels.BooksViewModelFactory
import com.test.palmhrdemo.viewmodels.SharedViewModel


class BooksFragment : Fragment() {
    private lateinit var binding: FragmentBooksBinding
    private  lateinit  var booksObserver: Observer<Resource<GeneralResponse>?>
    private lateinit var contentView: View
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var booksViewModel: BookViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // DI can be used to inject repo but for sake of assignment, we are directly initialsing
        val viewModelFactory = BooksViewModelFactory(
            repository = BooksRepository()
        )
        booksViewModel = ViewModelProvider(this, viewModelFactory)[BookViewModel::class.java]


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        if (!::contentView.isInitialized) {
            binding = FragmentBooksBinding.inflate(inflater, container, false)
            contentView = binding.root
            setupObservers()
            initViews()
        }
      setupObservers()

        return contentView;
    }

    private fun setupObservers() {
        booksObserver = Observer {

            when (it?.status) {
                AppEnums.Status.LOADING -> {

                }
                AppEnums.Status.SUCCESS -> {
                    booksViewModel?.getBooks("","","",true)?.removeObserver(booksObserver)
                    showData(it.data?.items)
                    binding.recordLayout.animationViewBig.gone()
                    binding.recordLayout.recordsRecyclerView.show()

                }
                AppEnums.Status.ERROR -> {
                    binding.recordLayout.animationViewBig.gone()
                    val ss = ""

                }
                else -> {}
            }
        }

        sharedViewModel.getSelectedFilterData().observe(viewLifecycleOwner) {
            if (it != null) {

                val query = TextUtil.makeQuery(it)
                if (query.isNotEmpty())
                    searchBook(query, true)
                else
                    resetSearch()
            } else {
                resetSearch()
            }

        }
    }

    private fun initViews() {
        binding.clearFilterMaterialTextView.setOnClickListener {
            sharedViewModel.setSelectedFilterData(null)
        }

        binding.searchMaterialCardView.setOnClickListener {
            openFilter()
        }
        // Initial Temp Catalog
        searchBook("dubai")


    }

    private fun resetSearch() {
        changeSearchTitle(getString(R.string.searchHint_txt))
        searchBook("dubai")
    }

    private fun changeSearchTitle(title: String) {
        binding.searchMaterialTextView.text = title
    }

    private fun openFilter() {
        val filterFragment = FilterFragment()
        filterFragment.show(childFragmentManager, "FILTER")
    }

    private fun searchBook(q: String, changeTitle: Boolean = false) {
        binding.recordLayout.animationViewBig.show()
        binding.recordLayout.recordsRecyclerView.gone()
        binding.recordLayout.emptyTextView.gone()
        if (changeTitle)
            changeSearchTitle(q)
        booksViewModel?.getBooks(q, Constants.MAX_RESULTS, "1")?.observe(viewLifecycleOwner,booksObserver)
    }

    private fun handleFilters(filterObject: FilterObject?) {

    }


    private fun showData(it: List<Items>?) {
        binding.recordLayout.apply {
            recordsRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recordsRecyclerView.adapter = BooksAdapter(it ?: emptyList(), requireActivity()) {
                if (it?.selfLink.isNullOrBlank())
                    Toast.makeText(requireContext(), getString(R.string.details_notAvailable_txt), Toast.LENGTH_SHORT)
                        .show()
                else
                    it?.selfLink?.let {
                        findNavController().navigate(
                            BooksFragmentDirections.actionBooksFragmentToBookDetailFragment(
                                it
                            )
                        )
                    }

            }
            emptyTextView.visibility =
                if (!it.isNullOrEmpty()) View.GONE else View.VISIBLE
            emptyTextView.setText(getString(R.string.noResultFound_msg))
        }

    }

}
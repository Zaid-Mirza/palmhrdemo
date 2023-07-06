package com.test.palmhrdemo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.test.palmhrdemo.databinding.FragmentBooksBinding
import com.test.palmhrdemo.models.FilterObject
import com.test.palmhrdemo.models.Items
import com.test.palmhrdemo.repositories.BooksRepository
import com.test.palmhrdemo.utils.AppEnums
import com.test.palmhrdemo.viewmodels.BookViewModel
import com.test.palmhrdemo.viewmodels.BooksViewModelFactory
import com.test.palmhrdemo.viewmodels.SharedViewModel


class BooksFragment : Fragment() {
    private lateinit var binding: FragmentBooksBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var booksViewModel: BookViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        binding = FragmentBooksBinding.inflate(inflater, container, false)
        initViews()
        return binding.root;
    }

    private fun initViews() {
        booksViewModel?.getBooks("dubai", "20", "1")?.observe(viewLifecycleOwner) {

            when (it?.status) {
                AppEnums.Status.LOADING -> {}
                AppEnums.Status.SUCCESS -> {
                    val ss = ""
//                    binding.recordLayout.animationViewBig.gone()
//                    binding.recordLayout.recordsRecyclerView.show()

                }
                AppEnums.Status.ERROR -> {
                    // binding.recordLayout.animationViewBig.gone()
                    val ss = ""

                }
                else -> {}
            }
        }

    }

    private fun handleFilters(filterObject: FilterObject?) {

    }

    private fun showData(it: List<Items>?) {
    }


}
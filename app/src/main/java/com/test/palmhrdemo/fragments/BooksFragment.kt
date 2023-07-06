package com.test.palmhrdemo.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.test.newsline.models.FilterObject
import com.test.palmhrdemo.databinding.FragmentBooksBinding
import com.test.palmhrdemo.models.Items
import com.test.palmhrdemo.repositories.BooksRepository
import com.test.palmhrdemo.viewmodels.BooksViewModelFactory
import com.test.palmhrdemo.viewmodels.SharedViewModel


const val PHONE_DETAIL = "phone_detail";

class BooksFragment : Fragment() {
    private lateinit var binding: FragmentBooksBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = BooksViewModelFactory(
            repository = BooksRepository(
            )
        )
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


    }

    private fun handleFilters(filterObject: FilterObject?) {

    }

    private fun showData(it: List<Items>?) {

    }



}
package com.test.palmhrdemo.fragments


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.test.palmhrdemo.R
import com.test.palmhrdemo.databinding.FragmentFilterBinding
import com.test.palmhrdemo.models.FilterObject
import com.test.palmhrdemo.viewmodels.SharedViewModel
import kotlinx.coroutines.*


class FilterFragment : BottomSheetDialogFragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentFilterBinding
    private var filterObject = FilterObject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFilterBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cancelMaterialButton.setOnClickListener {
            dismissAllowingStateLoss()
        }

        sharedViewModel.getSelectedFilterData().observe(viewLifecycleOwner) {
            if (it != null) {
                filterObject = it

                setFilterData()

            }


        }

        binding.okMaterialButton.setOnClickListener {
            val objects = FilterObject()
            binding.apply {
                objects.query = binding.queryTextField.text.toString()
                objects.author = authorTextField.text.toString()
                objects.isbn = isbnTextField.text.toString()
                objects.publisher = publisherTextField.text.toString()
            }


            sharedViewModel.setSelectedFilterData(objects)
            dismissAllowingStateLoss()
        }

    }


    private fun setFilterData() {
        binding.apply {
            queryTextField.setText(filterObject.query)
            authorTextField.setText(filterObject.author)
            isbnTextField.setText(filterObject.isbn)
            publisherTextField.setText(filterObject.publisher)
        }


    }


}
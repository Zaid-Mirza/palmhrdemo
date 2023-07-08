package com.test.palmhrdemo.fragments

import android.os.Bundle
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.test.palmhrdemo.R
import com.test.palmhrdemo.databinding.FragmentBookDetailBinding
import com.test.palmhrdemo.models.Items
import com.test.palmhrdemo.repositories.BooksRepository
import com.test.palmhrdemo.utils.AppEnums
import com.test.palmhrdemo.viewmodels.BookViewModel
import com.test.palmhrdemo.viewmodels.BooksViewModelFactory
import com.test.palmhrdemo.viewmodels.SharedViewModel


class BookDetailFragment : Fragment() {

    private lateinit var binding: FragmentBookDetailBinding
    private var booksViewModel: BookViewModel? = null

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val args: BookDetailFragmentArgs by navArgs()
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
        binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.apply {
            backArrowAppCompatImageView.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        booksViewModel?.getBookDetail(args.selfLink)?.observe(viewLifecycleOwner) {

            when (it?.status) {
                AppEnums.Status.LOADING -> {}
                AppEnums.Status.SUCCESS -> {
                    val ss = ""
                    populateData(it.data)
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

    private fun populateData(item: Items?) {
        item?.volumeInfo?.imageLinks?.thumbnail?.let {
            Glide.with(requireActivity()).load(it).placeholder(R.drawable.ic_placeholder)
                .into(binding.bookImageView)

        }
        binding.apply {
            publishedMessageMaterialTextView.text =
                String.format("%s %s %s %s", "Published By", item?.volumeInfo?.publisher,"on", item?.volumeInfo?.publishedDate )
            authorsNamesMaterialTextView.text = item?.volumeInfo?.authors?.joinToString(" | ") ?: getString(R.string.none_txt)
            pageCountMaterialTextView.text = String.format("%s %s","Total Pages",item?.volumeInfo?.pageCount.toString())
            textAppCompatImageView.setImageResource(if(item?.volumeInfo?.readingModes?.text == true) R.drawable.ic_check else R.drawable.ic_cross)
            imageAppCompatImageView.setImageResource(if(item?.volumeInfo?.readingModes?.image == true) R.drawable.ic_check else R.drawable.ic_cross)
            readOnWebMaterialTextView.text = String.format("%s %s","Read on Web",item?.accessInfo?.webReaderLink)
            Linkify.addLinks(readOnWebMaterialTextView,Linkify.WEB_URLS)
        }

    }

}
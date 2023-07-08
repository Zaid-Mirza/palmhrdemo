package com.test.palmhrdemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.palmhrdemo.R
import com.test.palmhrdemo.databinding.RowBooksItemBinding
import com.test.palmhrdemo.models.Items

class BooksAdapter(
    private val books: List<Items?>,
    private val context: Context,
    val listener: (Items?) -> Unit
) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {
    private lateinit var binding: RowBooksItemBinding

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RowBooksItemBinding.inflate(LayoutInflater.from(context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.onBind(books[position])

    }


    inner class ViewHolder(val binding: RowBooksItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(bookDetail: Items?) {
            binding.apply {
                bookDetail?.volumeInfo?.apply {
                    titleMaterialTextView.text = title ?: context.getString(R.string.none_txt)
                    subtitleMaterialTextView.text = subtitle ?: context.getString(R.string.none_txt)
                    authorMaterialTextView.text = authors.firstOrNull()?: context.getString(R.string.none_txt)
                    publishedDateMaterialTextView.text = publishedDate ?: context.getString(R.string.none_txt)
                    imageLinks?.smallThumbnail?.let {
                        Glide.with(context).load(it).into(bookImage)

                    }
                }




                itemView.setOnClickListener {
                    listener(bookDetail)
                }
            }


        }


    }


}
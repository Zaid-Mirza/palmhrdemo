package com.test.palmhrdemo.activities


import ae.rightfarm.fragments.PHONE_DETAIL

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.test.palmhrdemo.R

class ActivityDetail : AppCompatActivity() {
//    private lateinit var binding: ActivityDetailBinding
//    private lateinit var phoneDetail: PhoneDetail
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityDetailBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        if (intent.hasExtra(PHONE_DETAIL)) {
//            phoneDetail = intent.getSerializableExtra(PHONE_DETAIL) as PhoneDetail
//        }
//
//
//
//        setUpToolBar(binding.toolbar, getString(R.string.details_txt))
//        initViews()
//    }
//
//    private fun setUpToolBar(toolBar: Toolbar, title: String, subtitle: String = "") {
//
//        toolBar.visibility = View.VISIBLE
//        toolBar.title = title
//        toolBar.subtitle = subtitle
//        toolBar.navigationIcon =
//            ContextCompat.getDrawable(this, R.drawable.ic_baseline_arrow_back_24)
//        toolBar.setNavigationOnClickListener { finish() }
//        setSupportActionBar(toolBar)
//    }
//
//    private fun initViews() {
//
//        Glide.with(this).load(phoneDetail.phonePicture)
//            .placeholder(R.drawable.ic_placeholder).into(binding.phoneImageView)
//        binding.emptyStateLayout.recordsRecyclerView.layoutManager = LinearLayoutManager(this)
//        val stringObject = ServerUtils.getGSONConfiguration().toJson(phoneDetail)
//        val map = ServerUtils.getGSONConfiguration()
//            .fromJson<HashMap<String, Any>>(stringObject, HashMap::class.java)
//        map.remove("tid")
//        map.remove("id")
//        map.remove("picture")
//        map.keys.toList()
//
//        binding.emptyStateLayout.recordsRecyclerView.adapter = DetailAdapter(
//            map,
//            ArrayList(map.keys)
//        )
//    }
}
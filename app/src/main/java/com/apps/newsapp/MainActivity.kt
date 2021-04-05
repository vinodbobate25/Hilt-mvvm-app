package com.apps.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.newsapp.data.model.ArticlesItem
import com.apps.newsapp.news.APINewsApdater
import com.apps.newsapp.news.ListViewModel
import com.apps.newsapp.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val  listViewModel: ListViewModel by viewModels()
    private lateinit var adapter:APINewsApdater
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupObserver()
    }

    private  fun setupUI()
    {
     recyclerView.layoutManager=LinearLayoutManager(this)
       adapter=APINewsApdater(arrayListOf())
     recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context,(recyclerView.layoutManager as LinearLayoutManager).orientation))
        recyclerView.adapter=adapter

    }


    private  fun showNewsList( newList:List<ArticlesItem>)
    {
        adapter.notifyDataSetChanged()
       adapter.addData(newList)

    }

    private fun setupObserver()
    {
    listViewModel.getNews().observe(this, Observer {
        when(it.status)
        {
            Status.SUCCESS->
            {
            progressBar.visibility= View.GONE
                recyclerView.visibility=View.VISIBLE
                it.data?.let { it1 -> showNewsList(it1) }
            }
            Status.LOADING -> {
                progressBar.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
            Status.ERROR -> {
                //Handle Error
                progressBar.visibility = View.GONE
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        }
        })
    }
}
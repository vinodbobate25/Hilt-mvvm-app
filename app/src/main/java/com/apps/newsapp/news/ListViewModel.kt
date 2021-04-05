package com.apps.newsapp.news

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apps.newsapp.data.NewsRepository
import com.apps.newsapp.data.model.ArticlesItem
import com.apps.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val newsRepository: NewsRepository):ViewModel(){
    private val listlivedata=MutableLiveData<Resource<List<ArticlesItem>>>()

    init {
        fetchUser()
    }

    private  fun fetchUser()
    {
        viewModelScope.launch {
            listlivedata.postValue(Resource.loading(null))
            try {
                
            val newsFromAPI=newsRepository.getNews()
                listlivedata.postValue(Resource.success(newsFromAPI.articles!!))
            }
            catch (e:Exception)
            {
                listlivedata.postValue(Resource.error("ERROR ouccred "+e.printStackTrace(),null))
            }
        }
    }

    fun getNews():LiveData<Resource<List<ArticlesItem>>>
    {
        return  listlivedata
    }
}
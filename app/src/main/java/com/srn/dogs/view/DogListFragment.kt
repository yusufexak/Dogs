package com.srn.dogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.srn.dogs.R
import com.srn.dogs.adapter.DogRecyclerAdapter
import com.srn.dogs.viewModel.DogListViewModel
import kotlinx.android.synthetic.main.fragment_dog_list.*


class DogListFragment : Fragment() {
    private lateinit var viewModel:DogListViewModel
    private val recyclerDogAdapter=DogRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dog_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DogListViewModel::class.java)
        viewModel.refleshData()
        dogsRecycler.layoutManager=LinearLayoutManager(context)
        dogsRecycler.adapter=recyclerDogAdapter

        swipeRefleshLayout.setOnRefreshListener {
            dogsLoading.visibility=View.VISIBLE
            dogsErrorMessage.visibility=View.GONE
            dogsRecycler.visibility=View.GONE
            viewModel.refleshData()
            swipeRefleshLayout.isRefreshing=false
        }
        observeLiveData()

    }
    fun observeLiveData(){
        viewModel.dogs.observe(viewLifecycleOwner, Observer {dogs->
            dogs?.let {
                dogsRecycler.visibility=View.VISIBLE
                recyclerDogAdapter.dogListUpdate(dogs)
            }
        })
        viewModel.dogErrorMessage.observe(viewLifecycleOwner, Observer {error->
            error?.let {
                if (it){
                    dogsRecycler.visibility=View.GONE
                    dogsErrorMessage.visibility=View.VISIBLE
                }else{
                    dogsErrorMessage.visibility=View.GONE
                }
            }
        })
        viewModel.dogLoading.observe(viewLifecycleOwner, Observer {loading->
            loading?.let {
                if (it){
                    dogsRecycler.visibility=View.GONE
                    dogsErrorMessage.visibility=View.GONE
                    dogsLoading.visibility=View.VISIBLE
                }else{
                    dogsLoading.visibility=View.GONE

                }
            }
        })
    }

}
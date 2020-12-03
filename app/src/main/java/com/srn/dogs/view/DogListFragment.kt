package com.srn.dogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
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
    }


}
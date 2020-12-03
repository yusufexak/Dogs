package com.srn.dogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.srn.dogs.R
import com.srn.dogs.viewModel.DogDetailViewModel
import kotlinx.android.synthetic.main.fragment_dog_detail.*

class DogDetailFragment : Fragment() {

    private lateinit var viewModel : DogDetailViewModel
    private  var dogId=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dog_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProviders.of(this).get(DogDetailViewModel::class.java)
        viewModel.roomDataGet()

        arguments?.let {
            dogId= DogDetailFragmentArgs.fromBundle(it).dogId
            println(dogId)
        }
        observeLiveData()

    }
    fun observeLiveData(){
        viewModel.dog.observe(viewLifecycleOwner, Observer {dog->
            dog?.let {
                detailCode.text=it.code
                detailDescription.text = it.description
            }
        })
    }


}
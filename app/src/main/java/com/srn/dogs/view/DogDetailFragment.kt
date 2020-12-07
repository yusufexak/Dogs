package com.srn.dogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.srn.dogs.R
import com.srn.dogs.databinding.FragmentDogDetailBinding
import com.srn.dogs.viewModel.DogDetailViewModel

class DogDetailFragment : Fragment() {

    private lateinit var viewModel : DogDetailViewModel
    private  var dogId=0;
    private lateinit var dataBinding:FragmentDogDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_dog_detail,container,false)
        //return inflater.inflate(R.layout.fragment_dog_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            dogId= DogDetailFragmentArgs.fromBundle(it).dogId
        }

        viewModel= ViewModelProviders.of(this).get(DogDetailViewModel::class.java)
        viewModel.roomDataGet(dogId)


        observeLiveData()

    }
    fun observeLiveData(){
        viewModel.dog.observe(viewLifecycleOwner, Observer {dog->
            dog?.let {
                dataBinding.selectedDog = it
                /*
                detailCode.text=it.code.toString()
                detailDescription.text = it.description
                context?.let {
                    detailDogImageView.imageDownload(dog._imageUrl, placeHolderCreate(it))
                }

                 */
            }
        })
    }


}
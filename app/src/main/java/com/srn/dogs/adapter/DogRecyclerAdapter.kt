package com.srn.dogs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.srn.dogs.R
import com.srn.dogs.databinding.DogRecyclerRowBinding
import com.srn.dogs.model.Dog
import com.srn.dogs.util.extension.imageDownload
import com.srn.dogs.util.functions.placeHolderCreate
import com.srn.dogs.view.DogListFragmentDirections
import kotlinx.android.synthetic.main.dog_recycler_row.view.*
import java.util.*

class DogRecyclerAdapter(val dogList: ArrayList<Dog>) :RecyclerView.Adapter<DogRecyclerAdapter.DogViewHolder>(),IDogClickListener{
    class DogViewHolder(var view: DogRecyclerRowBinding):RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view= inflater.inflate(R.layout.dog_recycler_row,parent,false)
        val view =DataBindingUtil.inflate<DogRecyclerRowBinding>(inflater,R.layout.dog_recycler_row,parent,false)
        return  DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.dog=dogList[position]
        holder.view.listener=this
        /*
        holder.itemView.setOnClickListener {
            val action = DogListFragmentDirections.actionDogListFragmentToDogDetailFragment(dogList.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.itemView.imageView.imageDownload(dogList[position]._imageUrl, placeHolderCreate(holder.itemView.context))

         */
    }

    override fun getItemCount(): Int {
        return dogList.size
    }
    fun dogListUpdate(newDogList:List<Dog>){
        dogList.clear()
        dogList.addAll(newDogList)
        notifyDataSetChanged()
    }

    override fun dogClick(view: View) {
        val uuid =view.dogUuid.text.toString().toIntOrNull()
        uuid?.let {

            val action = DogListFragmentDirections.actionDogListFragmentToDogDetailFragment(it)
            Navigation.findNavController(view).navigate(action)
        }
    }
}
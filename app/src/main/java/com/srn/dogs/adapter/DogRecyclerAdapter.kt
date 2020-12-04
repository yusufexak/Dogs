package com.srn.dogs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.srn.dogs.R
import com.srn.dogs.model.Dog
import com.srn.dogs.util.extension.imageDownload
import com.srn.dogs.util.functions.placeHolderCreate
import com.srn.dogs.view.DogListFragmentDirections
import kotlinx.android.synthetic.main.dog_recycler_row.view.*

class DogRecyclerAdapter(val dogList:ArrayList<Dog>) :RecyclerView.Adapter<DogRecyclerAdapter.DogViewHolder>(){
    class DogViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view= inflater.inflate(R.layout.dog_recycler_row,parent,false)
        return  DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val action = DogListFragmentDirections.actionDogListFragmentToDogDetailFragment(0)
            Navigation.findNavController(it).navigate(action)
        }
        holder.itemView.imageView.imageDownload(dogList[position].imageUrl, placeHolderCreate(holder.itemView.context))
    }

    override fun getItemCount(): Int {
        return dogList.size
    }
    fun dogListUpdate(newDogList:List<Dog>){
        dogList.clear()
        dogList.addAll(newDogList)
        notifyDataSetChanged()
    }
}
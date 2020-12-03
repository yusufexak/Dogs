package com.srn.dogs.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.srn.dogs.R
import com.srn.dogs.model.Dog
import kotlinx.android.synthetic.main.fragment_dog_detail.view.*

class DogRecyclerAdapter(val dogList:ArrayList<Dog>) :RecyclerView.Adapter<DogRecyclerAdapter.DogViewHolder>(){
    class DogViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view= inflater.inflate(R.layout.dog_recycler_row,parent,false)
        return  DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.itemView.detailCode.text=dogList.get(position).code
        holder.itemView.detailDescription.text=dogList.get(position).description
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
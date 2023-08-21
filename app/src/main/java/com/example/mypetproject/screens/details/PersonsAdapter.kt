package com.example.mypetproject.screens.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mypetproject.R
import com.example.mypetproject.api.Person
import com.example.mypetproject.databinding.ItemPersonBinding

class PersonsAdapter(): RecyclerView.Adapter<PersonsAdapter.PersonViewHolder>() {

    override fun getItemCount(): Int = persons.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPersonBinding.inflate(inflater, parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = persons[position]
        with(holder.binding) {
            personNameTV.text = person.name
            if (person.photo.isNotBlank()){
                Glide.with(personPhotoIV.context)
                    .load(person.photo)
                    .placeholder(R.drawable.image_search)
                    .error(R.drawable.image_search)
                    .into(personPhotoIV)
            } else {
                personPhotoIV.setImageResource(R.drawable.image_search)
            }
        }
    }

    class PersonViewHolder(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {}


    private val diffCallback = object : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var persons: List<Person>
        get() = differ.currentList
        set(value) { differ.submitList(value) }
}
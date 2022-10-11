package com.katerina.pocket_interview.core.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.katerina.pocket_interview.core.ui.items.CollectionItem
import com.katerina.pocket_interview.databinding.ItemCardBinding

class CollectionsAdapter(
    private val collectionsList: List<CollectionItem>
) : RecyclerView.Adapter<CollectionsAdapter.CollectionsViewHolder>()  {

    inner class CollectionsViewHolder(val binding: ItemCardBinding)
        : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionsViewHolder, position: Int) {
        with(holder) {
            with(collectionsList[position]) {
                binding.txtCollectionTitle.text = this.title
                binding.txtCollectionTitle.text = this.tag
            }
        }
    }

    override fun getItemCount(): Int = collectionsList.size
}
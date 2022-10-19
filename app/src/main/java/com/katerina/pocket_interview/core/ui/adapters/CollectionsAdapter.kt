package com.katerina.pocket_interview.core.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.katerina.pocket_interview.core.ui.items.CollectionItem
import com.katerina.pocket_interview.databinding.ItemCollectionBinding

open class CollectionsAdapter(query: Query, private val listener: OnCollectionSelectedListener) :
    FirestoreAdapter<CollectionsAdapter.CollectionViewHolder>(query)  {

    interface OnCollectionSelectedListener {
        fun onCollectionSelected(collection: DocumentSnapshot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val binding = ItemCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CollectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.bind(getSnapshot(position), listener)
    }

    inner class CollectionViewHolder(val binding: ItemCollectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(snapshot: DocumentSnapshot, listener: OnCollectionSelectedListener?) {

            val collection = snapshot.toObject<CollectionItem>() ?: return

            binding.txtCollectionTitle.text = collection.title
            binding.txtCollectionTag.text = collection.tag

            binding.root.setOnClickListener {
                listener?.onCollectionSelected(snapshot)
            }
        }
    }
}
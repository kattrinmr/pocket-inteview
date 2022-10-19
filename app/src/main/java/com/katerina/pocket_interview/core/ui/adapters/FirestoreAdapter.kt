package com.katerina.pocket_interview.core.ui.adapters

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*

abstract class FirestoreAdapter<VH : RecyclerView.ViewHolder>(private var query: Query) :
    RecyclerView.Adapter<VH>(),
    EventListener<QuerySnapshot> {

    private var registration: ListenerRegistration? = null
    private val snapshots = ArrayList<DocumentSnapshot>()

    fun startListening() {
        if (registration == null) registration = query.addSnapshotListener(this)
    }

    fun stopListening() {
        registration?.remove()
        registration = null

        snapshots.clear()
        notifyDataSetChanged()
    }

    fun setQuery(query: Query) {
        startListening()

        snapshots.clear()
        notifyDataSetChanged()

        this.query = query
        startListening()
    }

    open fun onError(e: FirebaseFirestoreException) {
        Log.w(TAG, "onError", e)
    }

    open fun onDataChanged() {}

    override fun getItemCount(): Int {
        return snapshots.size
    }

    protected fun getSnapshot(index: Int): DocumentSnapshot {
        return snapshots[index]
    }

    override fun onEvent(documentSnapshots: QuerySnapshot?, e: FirebaseFirestoreException?) {

        if (e != null) {
            Log.w(TAG, "onEvent:error", e)
        }

        if (documentSnapshots != null) {
            for (change in documentSnapshots.documentChanges) {
                when (change.type) {
                    DocumentChange.Type.ADDED -> {
                        onDocumentAdded(change)
                    }

                    DocumentChange.Type.MODIFIED -> {
                        onDocumentModified(change)
                    }

                    DocumentChange.Type.REMOVED -> {
                        onDocumentRemoved(change)
                    }
                }
            }
        }

        onDataChanged()
    }

    private fun onDocumentAdded(change: DocumentChange) {
        snapshots.add(change.newIndex, change.document)
        notifyItemInserted(change.newIndex)
    }

    private fun onDocumentModified(change: DocumentChange) {
        if (change.oldIndex == change.newIndex) {
            snapshots[change.oldIndex] = change.document
            notifyItemChanged(change.oldIndex)
        } else {
            snapshots.removeAt(change.oldIndex)
            snapshots.add(change.newIndex, change.document)
            notifyItemMoved(change.oldIndex, change.newIndex)
        }
    }

    private fun onDocumentRemoved(change: DocumentChange) {
        snapshots.removeAt(change.oldIndex)
        notifyItemRemoved(change.oldIndex)
    }

    companion object {
        private const val TAG = "FirestoreAdapter"
    }
}
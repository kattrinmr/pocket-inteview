package com.katerina.pocket_interview.home.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.katerina.pocket_interview.R
import com.katerina.pocket_interview.core.ui.items.CollectionItem
import com.katerina.pocket_interview.databinding.FragmentCreateCollectionBinding
import com.katerina.pocket_interview.databinding.FragmentHomeBinding

class CreateCollectionFragment : DialogFragment() {

    private lateinit var binding: FragmentCreateCollectionBinding

    lateinit var firestore: FirebaseFirestore
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseFirestore.setLoggingEnabled(true)
        firestore = Firebase.firestore
        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()

        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes

        params.width = FrameLayout.LayoutParams.MATCH_PARENT
        params.height = FrameLayout.LayoutParams.WRAP_CONTENT

        dialog!!.window!!.attributes = params as WindowManager.LayoutParams

        binding.btnCreate.setOnClickListener {

            val title = binding.etTitle.text.toString()
            val tag = binding.etTag.text.toString()

            if (title.isEmpty() && tag.isEmpty())
                Toast.makeText(requireContext(), "Title and Tag should not be empty!!!", Toast.LENGTH_SHORT).show()
            else {
                val collectionRef = auth.currentUser?.uid?.let { uid ->
                    firestore.collection("users")
                        .document(uid)
                        .collection("collections")
                }

                val collection = createCollection(title, tag)

                collectionRef?.add(collection)

                findNavController().navigate(
                    CreateCollectionFragmentDirections.actionCreateCollectionFragmentToHomeFragment()
                )
            }
        }
    }

    private fun createCollection(title: String, tag: String) = CollectionItem(title, tag)
}
package com.katerina.pocket_interview.profile.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.katerina.pocket_interview.R
import com.katerina.pocket_interview.auth.ui.activities.AuthActivity
import com.katerina.pocket_interview.core.domain.models.UserModel
import com.katerina.pocket_interview.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        firestore = Firebase.firestore

        val userRef = firestore.collection("users").document(auth.currentUser!!.uid)

        userRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {

                val userInfo = snapshot.toObject<UserModel>()

                binding.txtProfileFullname.text = userInfo?.fullname
                binding.txtProfileEmail.text = userInfo?.email
            } else {
                binding.txtProfileFullname.text = ""
                binding.txtProfileEmail.text = ""
            }
        }
    }

    override fun onStart() {
        super.onStart()

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        // TODO: Убрать обращение к FirebaseAuth в ProfileFragment
        binding.btnExit.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(requireActivity(), AuthActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

    }
}
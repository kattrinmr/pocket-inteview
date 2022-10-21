package com.katerina.pocket_interview.home.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.katerina.pocket_interview.MyApplication
import com.katerina.pocket_interview.core.ui.adapters.CollectionsAdapter
import com.katerina.pocket_interview.core.utils.NetworkUtils
import com.katerina.pocket_interview.core.utils.ViewModelFactory
import com.katerina.pocket_interview.databinding.FragmentHomeBinding
import com.katerina.pocket_interview.home.ui.viewmodels.HomeFragmentViewModel
import javax.inject.Inject

class HomeFragment : Fragment(), CollectionsAdapter.OnCollectionSelectedListener {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private var query: Query? = null
    private var adapter: CollectionsAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<HomeFragmentViewModel>
    lateinit var homeViewModel: HomeFragmentViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = viewModelFactory.getViewModel(this)

        FirebaseFirestore.setLoggingEnabled(true)

        firestore = homeViewModel.getDb()
        auth = homeViewModel.getCurrentUser()

        query = auth.currentUser?.uid?.let {
            firestore.collection("users")
                .document(it)
                .collection("collections")
        }

        query?.let {
            adapter = object : CollectionsAdapter(it, this@HomeFragment) {

                override fun onDataChanged() {
                    if (!NetworkUtils.isOnline(requireContext())) {
                        binding.rvCollections.visibility = View.GONE
                        binding.txtRecyclerEmpty1.visibility = View.GONE
                        binding.txtRecyclerEmpty2.visibility = View.GONE
                        Snackbar.make(binding.root, "Нет подключения к интернету!", Snackbar.LENGTH_LONG).show()
                    } else {
                        if (itemCount == 0) {
                            binding.rvCollections.visibility = View.GONE
                            binding.txtRecyclerEmpty1.visibility = View.VISIBLE
                            binding.txtRecyclerEmpty2.visibility = View.VISIBLE
                        } else {
                            binding.rvCollections.visibility = View.VISIBLE
                            binding.txtRecyclerEmpty1.visibility = View.GONE
                            binding.txtRecyclerEmpty2.visibility = View.GONE
                        }
                    }
                }

                override fun onError(e: FirebaseFirestoreException) {
                    Snackbar.make(binding.root, "Error: check logs for info", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
            binding.rvCollections.adapter = adapter
        }

        binding.rvCollections.layoutManager = LinearLayoutManager(context)
    }

    override fun onStart() {
        super.onStart()

        adapter?.startListening()

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToCreateCollectionFragment()
            )
        }

        binding.imgAvatar.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToProfileFragment()
            )
        }
    }

    override fun onStop() {
        super.onStop()
        adapter?.stopListening()
    }

    override fun onCollectionSelected(collection: DocumentSnapshot) {
        Snackbar.make(binding.root, "Open collection", Snackbar.LENGTH_SHORT).show()
    }
}
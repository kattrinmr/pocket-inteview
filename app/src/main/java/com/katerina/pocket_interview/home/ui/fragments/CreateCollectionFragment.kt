package com.katerina.pocket_interview.home.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.katerina.pocket_interview.R
import com.katerina.pocket_interview.databinding.FragmentCreateCollectionBinding
import com.katerina.pocket_interview.databinding.FragmentHomeBinding

class CreateCollectionFragment : DialogFragment() {

    private lateinit var binding: FragmentCreateCollectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes

        params.width = FrameLayout.LayoutParams.MATCH_PARENT
        params.height = FrameLayout.LayoutParams.WRAP_CONTENT

        dialog!!.window!!.attributes = params as WindowManager.LayoutParams

        binding.btnCreate.setOnClickListener {
            findNavController().navigate(
                CreateCollectionFragmentDirections.actionCreateCollectionFragmentToHomeFragment2()
            )

        }
    }

    companion object {
        const val TAG = "CreateCollectionDialog"
    }

}
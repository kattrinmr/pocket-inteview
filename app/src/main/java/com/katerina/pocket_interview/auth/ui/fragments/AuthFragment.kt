package com.katerina.pocket_interview.auth.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.katerina.pocket_interview.MainActivity
import com.katerina.pocket_interview.MyApplication
import com.katerina.pocket_interview.R
import com.katerina.pocket_interview.auth.ui.viewmodels.AuthFragmentViewModel
import com.katerina.pocket_interview.core.utils.ViewModelFactory
import com.katerina.pocket_interview.core.utils.responses.ResponseStatus
import com.katerina.pocket_interview.databinding.FragmentAuthBinding
import javax.inject.Inject

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<AuthFragmentViewModel>
    lateinit var authViewModel: AuthFragmentViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel = viewModelFactory.getViewModel(this)

        authViewModel.authObservable.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> {}

                is ResponseStatus.Error -> {
                    Toast.makeText(requireContext(), status.throwable.message.toString(), Toast.LENGTH_SHORT).show()
                }

                is ResponseStatus.Success -> {
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
            }
        }

        binding.btnRegistration.setOnClickListener {
            findNavController().navigate(
                AuthFragmentDirections.actionAuthFragmentToRegistrationFragment()
            )
        }

        binding.btnLogin.setOnClickListener {
            auth()
        }
    }

    private fun auth() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        authViewModel.auth(email, password)
    }
}
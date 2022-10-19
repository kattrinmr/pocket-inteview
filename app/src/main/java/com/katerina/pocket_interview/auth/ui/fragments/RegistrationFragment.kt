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
import com.katerina.pocket_interview.auth.ui.viewmodels.RegistrationFragmentViewModel
import com.katerina.pocket_interview.core.utils.ViewModelFactory
import com.katerina.pocket_interview.core.utils.responses.ResponseStatus
import com.katerina.pocket_interview.databinding.FragmentRegistrationBinding
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<RegistrationFragmentViewModel>
    lateinit var registrationViewModel: RegistrationFragmentViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationViewModel = viewModelFactory.getViewModel(this)

        registrationViewModel.registrationObservable.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> {
                    showProgressBar()
                }

                is ResponseStatus.Error -> {
                    hideProgressBar()
                    Toast.makeText(requireContext(), status.throwable.message.toString(), Toast.LENGTH_SHORT).show()
                }

                is ResponseStatus.Success -> {
                    showProgressBar()
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        binding.btnRegistration.setOnClickListener {
            registerUser()
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun registerUser() {

        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()

        registrationViewModel.createUser(email, password, confirmPassword)
    }

    private fun showProgressBar() {

        binding.progressBar.visibility = View.VISIBLE

        binding.btnRegistration.visibility = View.GONE
        binding.btnBack.visibility = View.GONE
        binding.txtTitle.visibility = View.GONE
        binding.txtTitleRegistration.visibility = View.GONE
        binding.etFieldEmail.visibility = View.GONE
        binding.etFieldConfirmPassword.visibility = View.GONE
        binding.etFieldPassword.visibility = View.GONE
        binding.etFieldFirstName.visibility = View.GONE
        binding.etFieldLastName.visibility = View.GONE
        binding.etEmail.visibility = View.GONE
        binding.etConfirmPassword.visibility = View.GONE
        binding.etPassword.visibility = View.GONE
        binding.etFirstName.visibility = View.GONE
        binding.etLastName.visibility = View.GONE

    }

    private fun hideProgressBar() {

        binding.progressBar.visibility = View.GONE

        binding.btnRegistration.visibility = View.VISIBLE
        binding.btnBack.visibility = View.VISIBLE
        binding.txtTitle.visibility = View.VISIBLE
        binding.txtTitleRegistration.visibility = View.VISIBLE
        binding.etFieldEmail.visibility = View.VISIBLE
        binding.etFieldConfirmPassword.visibility = View.VISIBLE
        binding.etFieldPassword.visibility = View.VISIBLE
        binding.etFieldFirstName.visibility = View.VISIBLE
        binding.etFieldLastName.visibility = View.VISIBLE
        binding.etEmail.visibility = View.VISIBLE
        binding.etConfirmPassword.visibility = View.VISIBLE
        binding.etPassword.visibility = View.VISIBLE
        binding.etFirstName.visibility = View.VISIBLE
        binding.etLastName.visibility = View.VISIBLE

    }



}
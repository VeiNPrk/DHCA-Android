package com.vnprk.dhca.views

import android.content.Intent
import android.os.Bundle
import android.os.SharedMemory
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.vnprk.dhca.databinding.FragmentAuthorizationBinding
import com.vnprk.dhca.viewmodel.AuthorizationViewModel

class AuthorizationFragment : Fragment() {
    private val args: AuthorizationFragmentArgs by navArgs()
    private lateinit var binding: FragmentAuthorizationBinding
    private val viewModel: AuthorizationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        binding.btnAuthorization.setOnClickListener {
            binding.tilLogin.isErrorEnabled = false
            binding.tilPassword.isErrorEnabled = false
            activity?.currentFocus?.let {
                val imm: InputMethodManager = requireContext().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(it.windowToken, 0)
            }
            viewModel.authorizationOnClick(binding.tilLogin.editText?.text.toString(), binding.tilPassword.editText?.text.toString(), args.token)
        }
        binding.btnGoToRegistration.setOnClickListener {
            findNavController().navigate(AuthorizationFragmentDirections.actionAuthorizationFragmentToRegistrationFragment(args.token))
        }
        viewModel.stateUiLiveData.observe(viewLifecycleOwner, Observer { stateUi->
            binding.progressLayout.visibility = View.GONE
            when(stateUi){
                AuthorizationViewModel.STATE_UI_LOADING -> binding.progressLayout.visibility = View.VISIBLE
                AuthorizationViewModel.STATE_UI_GOTO_MAIN -> { startActivity(Intent(requireContext(), MainActivity::class.java)) }
                //AuthorizationViewModel.STATE_UI_GOTO_REGISTRATION -> findNavController().navigate(AuthorizationFragmentDirections.actionAuthorizationFragmentToRegistrationFragment(args.token))
                AuthorizationViewModel.STATE_UI_LOGIN_NOT_VALID -> {
                    binding.tilLogin.isErrorEnabled = true
                    binding.tilLogin.error = "Введите логин"
                }
                AuthorizationViewModel.STATE_UI_PASSWORD_NOT_VALID -> {
                    binding.tilPassword.isErrorEnabled = true
                    binding.tilPassword.error = "Введите пароль"
                }
                AuthorizationViewModel.STATE_UI_TOKEN_IS_EMPTY -> {
                    showMessage("Пустой токен")
                }
            }
        })
        viewModel.getLoadState().observe(viewLifecycleOwner, Observer {
            viewModel.observeState(requireContext(), it)
        })
        viewModel.errorsLiveData.observe(viewLifecycleOwner, Observer { error ->
            showMessage(error)
        })
        return binding.root
    }

    private fun showMessage(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
    }
}
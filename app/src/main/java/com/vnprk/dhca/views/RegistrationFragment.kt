package com.vnprk.dhca.views

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.vnprk.dhca.R
import com.vnprk.dhca.databinding.FragmentAuthorizationBinding
import com.vnprk.dhca.databinding.FragmentRegistrationBinding
import com.vnprk.dhca.viewmodel.AuthorizationViewModel

class RegistrationFragment : Fragment() {
    private val args: RegistrationFragmentArgs by navArgs()
    private lateinit var binding: FragmentRegistrationBinding
    private val viewModel: AuthorizationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        binding.btnDoRegistration.setOnClickListener {
            binding.tilLogin.isErrorEnabled = false
            binding.tilPassword.isErrorEnabled = false
            binding.tilRepeatePassword.isErrorEnabled = false
            viewModel.registrationOnClick(binding.tilLogin.editText?.text.toString(), binding.tilPassword.editText?.text.toString(), binding.tilRepeatePassword.editText?.text.toString(), args.token)
        //findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToAuthorizationFragment(args.token))
        }
        binding.btnRegistrationCancel.setOnClickListener {
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToAuthorizationFragment(args.token))
        }
        viewModel.stateUiLiveData.observe(viewLifecycleOwner, Observer { stateUi->
            binding.progressLayout.visibility = View.GONE
            when(stateUi){
                AuthorizationViewModel.STATE_UI_LOADING -> binding.progressLayout.visibility = View.VISIBLE
                AuthorizationViewModel.STATE_UI_GOTO_AUTHORIZATION -> {
                    val alertDialog: AlertDialog? = activity?.let {
                        val builder = AlertDialog.Builder(it)
                        builder.apply {
                            setTitle("Регистрация")
                            setMessage("Регистрация прошла успешно, можете авторизоваться под своим логином.")
                            setPositiveButton("Ok",
                                DialogInterface.OnClickListener { _, _ ->
                                    findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToAuthorizationFragment(args.token))
                                })
                            setNegativeButton("Отмена",
                                DialogInterface.OnClickListener { _, _ ->
                                })
                        }
                        builder.create()
                    }
                    alertDialog?.show()
                    //startActivity(Intent(requireContext(), MainActivity::class.java))
                }
                //AuthorizationViewModel.STATE_UI_GOTO_REGISTRATION -> findNavController().navigate(AuthorizationFragmentDirections.actionAuthorizationFragmentToRegistrationFragment(args.token))
                AuthorizationViewModel.STATE_UI_LOGIN_NOT_VALID -> {
                    binding.tilLogin.isErrorEnabled = true
                    binding.tilLogin.error = "Введите логин"
                }
                AuthorizationViewModel.STATE_UI_PASSWORD_NOT_VALID -> {
                    binding.tilPassword.isErrorEnabled = true
                    binding.tilPassword.error = "Введите пароль"
                }
                AuthorizationViewModel.STATE_UI_PASW_REPEATE_NOT_VALID -> {
                    binding.tilRepeatePassword.isErrorEnabled = true
                    binding.tilRepeatePassword.error = "Неверный пароль"
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
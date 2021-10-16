package com.vnprk.dhca.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.vnprk.dhca.R
import com.vnprk.dhca.SharedPreferencesUtils

class SplashFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onStart() {
        super.onStart()
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e("SplashFragment", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            val msg = "FCM registration Token: $token"
            Log.d("SplashFragment", msg)
            val sharedToken = SharedPreferencesUtils.getMyToken(requireContext())
            if(sharedToken !=""){
                if(sharedToken == token){
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                } else{
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToAuthorizationFragment(token!!))
                }
            } else{
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToAuthorizationFragment(token!!))
            }
        })
    }
}
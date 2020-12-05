package com.dispositivosmoveis.ritterflix.ui.more

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_more.*
import kotlinx.android.synthetic.main.fragment_sign_in.*

class MoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_more, container, false)
    }

    private var mAuth: FirebaseAuth? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        btn_logout!!.setOnClickListener {
            disconnected()
            logout()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): MoreFragment {
            return MoreFragment()
        }
    }

    private fun disconnected() {
        FirebaseAuth.getInstance().signOut()
    }

    private fun logout() {
        val intent = Intent(context, LoginActivity::class.java)
        activity?.finish()
        startActivity(intent)
    }
}
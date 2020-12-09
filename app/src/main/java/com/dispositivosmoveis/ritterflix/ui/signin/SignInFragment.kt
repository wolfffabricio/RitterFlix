package com.dispositivosmoveis.ritterflix.ui.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.ui.home.HomeActivity
import com.dispositivosmoveis.ritterflix.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        criar_conta_btn!!.setOnClickListener {
            signUpUser()
        }
        login_back_btn!!.setOnClickListener{
            backLogin()
        }

    }

    private fun signUpUser() {
        if (et_mailSignIn!!.text.toString().isEmpty()) {
            et_mailSignIn!!.error = "Por favor insira o e-mail"
            et_mailSignIn!!.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(et_mailSignIn!!.text.toString()).matches()) {
            et_mailSignIn!!.error ?: "Por favor coloque um e-mail válido"
            et_mailSignIn!!.requestFocus()
            return
        }
        if (et_password_criar!!.text.toString().isEmpty()) {
            et_password_criar!!.error = "Por favor insira o password"
            et_password_criar!!.requestFocus()
            return
        }
        loginUser(
            email = et_mailSignIn!!.text.toString(),
            password = et_password_criar!!.text.toString()
        )
    }

    private fun loginUser(email: String, password: String) {
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    Toast.makeText(context, "Conta criada OK!", Toast.LENGTH_SHORT).show()
                    openHomeActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun openHomeActivity() {
        val intent = Intent(context, HomeActivity::class.java)
        activity?.finish()
        startActivity(intent)
    }

    private fun backLogin() {
        val intent = Intent(context, LoginActivity::class.java)
        activity?.finish()
        startActivity(intent)
    }
}
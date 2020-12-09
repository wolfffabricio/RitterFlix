package com.dispositivosmoveis.ritterflix.ui.login

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
import com.dispositivosmoveis.ritterflix.ui.signin.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private var mAuth: FirebaseAuth? = null

    companion object {
        @JvmStatic
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        login_button!!.setOnClickListener {
            signUpUser()
        }
        open_account_button!!.setOnClickListener {
            openSignInActivity()
        }
    }

    private fun signUpUser() {
        if (et_email!!.text.toString().isEmpty()) {
            et_email!!.error = "Por favor insira o e-mail"
            et_email!!.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(et_email!!.text.toString()).matches()) {
            et_email!!.error ?: "Por favor coloque um e-mail válido"
            et_email!!.requestFocus()
            return
        }
        if (et_password!!.text.toString().isEmpty()) {
            et_password!!.error = "Por favor insira o password"
            et_password!!.requestFocus()
            return
        }
        loginUser(email = et_email!!.text.toString(), password = et_password!!.text.toString())
    }

    private fun loginUser(email: String, password: String) {
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    Toast.makeText(context, "Login OK!", Toast.LENGTH_SHORT).show()
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

    private fun userConnected(): Boolean {
        val currentUser = mAuth!!.currentUser
        return currentUser != null
    }

    override fun onStart() {
        super.onStart()
        if (switch_save_login.isChecked) {
            userConnected()
            openHomeActivity()
        } else {
            Toast.makeText(
                activity?.applicationContext,
                getString(R.string.invalid_credentials), Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun openHomeActivity() {
        val intent = Intent(context, HomeActivity::class.java)
        activity?.finish()
        startActivity(intent)
    }

    private fun openSignInActivity() {
        val intent = Intent(context, SignInActivity::class.java)
        activity?.finish()
        startActivity(intent)
    }
}
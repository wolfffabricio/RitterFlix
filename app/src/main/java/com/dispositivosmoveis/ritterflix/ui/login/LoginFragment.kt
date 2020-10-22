package com.dispositivosmoveis.ritterflix.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.ui.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()

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
        viewModel.validateCredentials(viewModel.getEmailFromPrefs(), viewModel.getPasswordFromPrefs()).observe(this, Observer {
            if (it) {
                activity?.finish()
                startActivity(Intent(activity?.applicationContext, HomeActivity::class.java))
            }
        })

        login_button.setOnClickListener {
            viewModel.validateCredentials(et_email.text.toString(), et_password.text.toString()).observe(this,
                Observer {
                    if (it) {
                        if (switch_save_login.isChecked) {
                            viewModel.saveLogin(et_email.text.toString(), et_password.text.toString())
                        }
                        activity?.finish()
                        startActivity(Intent(activity?.applicationContext, HomeActivity::class.java))
                    } else {
                        Toast.makeText(activity?.applicationContext, getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}
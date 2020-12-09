package com.dispositivosmoveis.ritterflix.ui.more

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dispositivosmoveis.ritterflix.R

class MoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_more, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(): MoreFragment {
            return MoreFragment()
        }
    }
}
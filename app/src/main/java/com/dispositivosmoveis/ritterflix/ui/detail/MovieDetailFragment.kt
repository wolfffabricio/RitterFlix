package com.dispositivosmoveis.ritterflix.ui.detail

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dispositivosmoveis.ritterflix.databinding.FragmentMovieDetailBinding
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailViewModel by viewModel()
    private var binding: FragmentMovieDetailBinding? = null

    companion object {
        private const val MOVIE_ID = "movie_id"

        fun newInstance(movieId: Int): MovieDetailFragment {
            val args = Bundle()
            args.putSerializable(MOVIE_ID, movieId)
            val fragment = MovieDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(detail_toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)

        val movieId = requireArguments().getSerializable(MOVIE_ID) as Int
        loader.visibility = View.VISIBLE
        viewModel.getMovieWithId(movieId)?.observe(viewLifecycleOwner, Observer {
            it.let {
                loader.visibility = View.INVISIBLE
                binding?.movie = it
            }
        })
    }

    private fun goBack() {
        activity?.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> goBack()
        }
        return super.onOptionsItemSelected(item)
    }
}
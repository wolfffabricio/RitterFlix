package com.dispositivosmoveis.ritterflix.ui.detail

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dispositivosmoveis.ritterflix.R
import com.dispositivosmoveis.ritterflix.databinding.FragmentMovieDetailBinding
import com.dispositivosmoveis.ritterflix.extensions.shareContentWithImage
import com.dispositivosmoveis.ritterflix.repository.http.BASE_POST_URL
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


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

    private fun shareMovie() {
        viewModel.movie.value.let {
            if (it != null) {
                val shareContent = "Olha que massa esse filme! Confira agora no RitterFlix.\n\n filmes.uniritter.edu.br/filme?id=" + it.id + "&de=Jean"

                Picasso.get()
                    .load(BASE_POST_URL + it.posterPath)
                    .into(object : Target {
                        override fun onBitmapLoaded(bitmap: Bitmap, from: LoadedFrom) {
                            getLocalBitmapUri(bitmap, activity?.applicationContext!!)?.let { it1 ->
                                shareContentWithImage(it1, shareContent)
                            }
                        }

                        override fun onPrepareLoad(placeHolderDrawable: Drawable) {}

                        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                            Log.e("Bitmap Failed: ", e.toString())
                        }
                    })
            }
        }
    }

    fun getLocalBitmapUri(bmp: Bitmap, context: Context): Uri? {
        var bmpUri: Uri? = null
        try {
            val file = File(
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "share_image_" + System.currentTimeMillis() + ".png"
            )
            val out = FileOutputStream(file)
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out)
            out.close()
            bmpUri = FileProvider.getUriForFile(context, context.packageName +".fileprovider", file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bmpUri
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.custom_menu_movie_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> goBack()
            R.id.share_action -> shareMovie()
        }
        return super.onOptionsItemSelected(item)
    }
}
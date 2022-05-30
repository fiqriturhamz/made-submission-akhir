package com.dicoding.capstoneakhir.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.RequestOptions.placeholderOf
import com.dicoding.capstoneakhir.R
import com.dicoding.capstoneakhir.core.Constant.DETAILS_POSTER_RADIUS
import com.dicoding.capstoneakhir.core.Constant.IMAGE_BASE_URL
import com.dicoding.capstoneakhir.core.domain.model.IMovie
import com.dicoding.capstoneakhir.databinding.ActivityDetailsBinding
import com.dicoding.capstoneakhir.databinding.ActivityDetailsBinding.inflate
import com.dicoding.capstoneakhir.databinding.ContentDetailsBinding
import jp.wasabeef.glide.transformations.BlurTransformation
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var activityDetailsBinding: ActivityDetailsBinding
    private lateinit var contentDetailsBinding: ContentDetailsBinding

    private val detailsViewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailsBinding = inflate(layoutInflater)
        activityDetailsBinding.apply {
            setContentView(root)
            contentDetailsBinding = details
        }

        intent.getParcelableExtra<IMovie>(EXTRA_DATA).also {
            showDetails(it)
        }
    }

    private fun showDetails(iMovie: IMovie?) {
        contentDetailsBinding.apply {
            iMovie?.apply {
                Glide
                    .with(this@DetailsActivity)
                    .load("$IMAGE_BASE_URL$backdrop")
                    .apply(placeholderOf(R.drawable.white_backdrop).error(R.drawable.white_backdrop))
                    .into(imgBackdrop)
                Glide
                    .with(this@DetailsActivity)
                    .load("$IMAGE_BASE_URL$poster")
                    .apply(placeholderOf(R.drawable.imovie).error(R.drawable.imovie))
                    .transform(RoundedCorners(DETAILS_POSTER_RADIUS))
                    .into(imgPoster)
                tvTitle.text = title
                tvRating.text = rating.toString()
                tvReleaseDate.text = releaseDate
                tvSynopsis.text = synopsis

                var watchlistStatus = watchlist

                setWatchlistStatus(watchlistStatus)
                btnFavorite.setOnClickListener {
                    watchlistStatus = !watchlistStatus
                    detailsViewModel.watchlist(this, watchlistStatus)
                    setWatchlistStatus(watchlistStatus)
                }
            }
        }
    }

    private fun setWatchlistStatus(watchlistStatus: Boolean) {
        contentDetailsBinding.btnFavorite.apply {
            if (watchlistStatus) {
                setImageDrawable(getDrawable(this@DetailsActivity, R.drawable.ic_favorite_true))
            } else {
                setImageDrawable(getDrawable(this@DetailsActivity, R.drawable.ic_favorite_false))
            }
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

}
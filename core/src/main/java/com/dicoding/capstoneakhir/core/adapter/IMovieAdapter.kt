package com.dicoding.capstoneakhir.core.adapter

import android.view.LayoutInflater.from as layoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions.placeholderOf
import com.dicoding.capstoneakhir.core.Constant.IMAGE_BASE_URL
import com.dicoding.capstoneakhir.core.Constant.ITEMS_POSTER_RADIUS
import com.dicoding.capstoneakhir.core.R
import com.dicoding.capstoneakhir.core.databinding.ItemListBinding
import com.dicoding.capstoneakhir.core.databinding.ItemListBinding.inflate as ItemListBinding
import com.dicoding.capstoneakhir.core.domain.model.IMovie

class IMovieAdapter : RecyclerView.Adapter<IMovieAdapter.IMovieViewHolder>() {

    private var iMovieList = ArrayList<IMovie>()
    var onItemClick: ((IMovie) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): IMovieViewHolder {
        viewGroup.apply {
            ItemListBinding(layoutInflater(context), this, false).also {
                return IMovieViewHolder(it)
            }
        }
    }

    override fun onBindViewHolder(holder: IMovieViewHolder, position: Int) {
        iMovieList[position].also {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = iMovieList.size

    fun setData(newMoviesList: List<IMovie>?) {
        if (newMoviesList == null) return
        iMovieList.apply {
            clear()
            addAll(newMoviesList)
            notifyDataSetChanged()
        }
    }

    inner class IMovieViewHolder(private val itemListBinding: ItemListBinding) : RecyclerView.ViewHolder(itemListBinding.root) {
        fun bind(iMovie: IMovie) {
            itemListBinding.apply {
                iMovie.apply {
                    itemView.apply {
                        Glide
                            .with(context)
                            .load("$IMAGE_BASE_URL$poster")
                            .transform(RoundedCorners(ITEMS_POSTER_RADIUS))
                            .apply(placeholderOf(R.drawable.imovie).error(R.drawable.ic_baseline_error_24))
                            .into(imgPoster)
                        tvTitle.text = title
                        tvRating.text = rating.toString()
                        tvReleaseDate.text = releaseDate
                        tvSynopsis.text = synopsis
                    }
                }
            }
        }

        init {
            itemListBinding.root.setOnClickListener {
                onItemClick?.invoke(iMovieList[absoluteAdapterPosition])
            }
        }
    }

}
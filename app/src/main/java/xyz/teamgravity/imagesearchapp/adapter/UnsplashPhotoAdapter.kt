package xyz.teamgravity.imagesearchapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import xyz.teamgravity.imagesearchapp.R
import xyz.teamgravity.imagesearchapp.databinding.CardUnsplashPhotoBinding
import xyz.teamgravity.imagesearchapp.model.UnsplashPhotoModel

class UnsplashPhotoAdapter(private val listener: OnUnsplashPhotoListener) :
    PagingDataAdapter<UnsplashPhotoModel, UnsplashPhotoAdapter.UnsplashPhotoViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<UnsplashPhotoModel>() {
            override fun areItemsTheSame(
                oldItem: UnsplashPhotoModel,
                newItem: UnsplashPhotoModel
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: UnsplashPhotoModel,
                newItem: UnsplashPhotoModel
            ) = oldItem == newItem
        }
    }

    inner class UnsplashPhotoViewHolder(private val binding: CardUnsplashPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: UnsplashPhotoModel) {
            binding.apply {
                Glide.with(itemView)
                    .load(model.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageI)

                userNameT.text = model.user.username
            }
        }

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val photo = getItem(position)
                    if (photo != null)
                        listener.onUnsplashPhotoClick(photo)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: UnsplashPhotoViewHolder, position: Int) {
        val model = getItem(position)

        if (model != null) {
            holder.bind(model)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnsplashPhotoViewHolder {
        val binding = CardUnsplashPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return UnsplashPhotoViewHolder(binding)
    }

    interface OnUnsplashPhotoListener {
        fun onUnsplashPhotoClick(photo: UnsplashPhotoModel)
    }
}
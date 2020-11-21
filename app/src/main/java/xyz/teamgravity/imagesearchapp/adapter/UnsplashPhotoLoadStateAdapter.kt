package xyz.teamgravity.imagesearchapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import xyz.teamgravity.imagesearchapp.databinding.CardFooterBinding

class UnsplashPhotoLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<UnsplashPhotoLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(private val binding: CardFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryB.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                retryB.isVisible = loadState !is LoadState.Loading
                errorT.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = CardFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }
}
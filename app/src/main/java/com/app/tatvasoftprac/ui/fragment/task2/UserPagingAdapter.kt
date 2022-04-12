package com.app.tatvasoftprac.ui.fragment.task2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.tatvasoftprac.R
import com.app.tatvasoftprac.databinding.ItemUserBinding
import com.app.tatvasoftprac.ktx.loadImage
import com.app.tatvasoftprac.model.UserResponse

class UserPagingAdapter :
    PagingDataAdapter<UserResponse.UserModel, UserPagingAdapter.UsersViewHolder>(
        PlayersDiffCallback()
    ) {

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UsersViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    inner class UsersViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val data = getItem(position)
            binding.let {
                it.userModel = data
                it.sivProfile.loadImage(data?.avatar ?: "", R.drawable.ic_person)
            }
        }
    }

    private class PlayersDiffCallback : DiffUtil.ItemCallback<UserResponse.UserModel>() {
        override fun areItemsTheSame(oldItem: UserResponse.UserModel, newItem: UserResponse.UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserResponse.UserModel, newItem: UserResponse.UserModel): Boolean {
            return oldItem == newItem
        }
    }
}
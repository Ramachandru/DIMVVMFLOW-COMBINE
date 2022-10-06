package com.example.dimvvmflow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dimvvmflow.databinding.PlayerListAdapterBinding
import com.example.dimvvmflow.model.User

class PlayerListAdapter() : RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>() {
    var playersData: List<User>? = null
    fun setUpData(playersData: List<User>) {
        this.playersData = playersData
    }

    private lateinit var playerListAdapterBinding: PlayerListAdapterBinding

    class PlayerViewHolder(itemView: PlayerListAdapterBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var binding = itemView
        fun executeDatas(data: User) {
            binding.player = data;
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        playerListAdapterBinding = PlayerListAdapterBinding.inflate(inflate, parent, false)
        return PlayerViewHolder(playerListAdapterBinding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        var player = playersData!!.get(position)
        holder.executeDatas(player)
    }

    override fun getItemCount(): Int {
        return playersData!!.size
    }
}
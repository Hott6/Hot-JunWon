package org.techtown.soptseminar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.soptseminar.databinding.ItemRepositorySampleListBinding

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    val repositoryList = mutableListOf<RepositoryData>()

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding =
            ItemRepositorySampleListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.onBind(repositoryList[position])
    }

    class RepositoryViewHolder(private val binding: ItemRepositorySampleListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //
        fun onBind(data: RepositoryData) {
            binding.tvTitle.text = data.title
            binding.tvDescription.text = data.description
        }
    }
}

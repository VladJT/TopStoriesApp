package jt.projects.topstoriesapp.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import jt.projects.topstoriesapp.R
import jt.projects.topstoriesapp.databinding.ItemStoryBinding
import jt.projects.topstoriesapp.model.Story

class StoryViewHolder private constructor(
    private val binding: ItemStoryBinding
) : RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    fun bind(
        data: Story,
        onItemClicked: ((Story) -> Unit)?
    ) {
        if (layoutPosition != RecyclerView.NO_POSITION) {
            with(binding) {
                tvTitle.text = data.title
                tvDescription.text = data.description

                ivImage.load(data.imageUrl) {
                    error(R.drawable.baseline_image_not_supported_24)
                }

                root.setOnClickListener { onItemClicked?.invoke(data) }
            }

        }
    }
}

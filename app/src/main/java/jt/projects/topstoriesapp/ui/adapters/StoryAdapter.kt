package jt.projects.topstoriesapp.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jt.projects.topstoriesapp.model.Story
import jt.projects.topstoriesapp.ui.viewholders.StoryViewHolder

class StoryAdapter(
    private var onItemClicked: ((Story) -> Unit)? = null,
) : RecyclerView.Adapter<StoryViewHolder>() {

    private var data: List<Story> = listOf()

    fun setData(data: List<Story>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StoryViewHolder(parent)

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(data[position], onItemClicked)
    }

    override fun getItemCount(): Int = data.size
}
package jt.projects.topstoriesapp.repository


import jt.projects.topstoriesapp.model.Story
import jt.projects.topstoriesapp.repository.dto.StoryDTO


fun StoryDTO.toStoryList(): List<Story> {
    return this.results.map { data ->
        Story(
            description = data.multimedia[0].caption ?: "",
            title = data.title ?: "no title",
            imageUrl = data.multimedia[0].url ?: ""
        )
    }

}
package jt.projects.topstoriesapp.repository


import jt.projects.topstoriesapp.model.Story
import jt.projects.topstoriesapp.repository.dto.StoryDTO

fun StoryDTO.toStoryList(): List<Story> {
    val result = mutableListOf<Story>()
    this.results.forEach { r ->
        result.add(
            Story(
                description = r.multimedia[0].caption ?: "",
                imageUrl = r.multimedia[0].url ?: ""
            )
        )
    }
    return result
}
package jt.projects.topstoriesapp.interactors

import jt.projects.topstoriesapp.repository.IStoryRepo


class StoryInteractor(
    private val repo: IStoryRepo
) {

    suspend fun getStories() = repo.getStories()
}
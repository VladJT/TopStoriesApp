package jt.projects.topstoriesapp.repository


import jt.projects.topstoriesapp.model.Story

interface IStoryRepo {
    suspend fun getStories(): List<Story>
}
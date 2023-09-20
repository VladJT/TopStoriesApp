package jt.projects.topstoriesapp.repository


import jt.projects.topstoriesapp.BuildConfig
import jt.projects.topstoriesapp.repository.dto.StoryDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface StoryAPI {
    @GET("svc/topstories/v2/world.json")
    suspend fun getStories(@Query("api-key") apiKey: String = BuildConfig.API_KEY): StoryDTO
}

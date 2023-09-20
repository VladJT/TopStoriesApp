package jt.projects.topstoriesapp.repository


import jt.projects.topstoriesapp.BuildConfig
import jt.projects.topstoriesapp.model.Story
import jt.projects.topstoriesapp.repository.BaseInterceptor.Companion.interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteStoryDataSource : IStoryRepo {

    private inline fun <reified T> getApi(): T = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(interceptor.createOkHttpClient()).build().create(T::class.java)


    override suspend fun getStories(): List<Story> = getApi<StoryAPI>().getStories().toStoryList()

}

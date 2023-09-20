package jt.projects.topstoriesapp.repository


import jt.projects.topstoriesapp.BuildConfig
import jt.projects.topstoriesapp.model.Story
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteStoryDataSource : IStoryRepo {

    private inline fun <reified T> getApi(): T = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient(BaseInterceptor.interceptor)).build().create(T::class.java)


    override suspend fun getStories(): List<Story> = getApi<StoryAPI>().getStories().toStoryList()

    /**
    В библиотеку можно внедрить перехватчики для изменения заголовков при помощи класса Interceptor из OkHttp.
    Сначала следует создать объект перехватчика и передать его в OkHttp, который в свою очередь следует явно подключить в
    Retrofit.Builder через метод client().
     */
    private fun createOkHttpClient(interceptor: Interceptor) =
        OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).build()
}

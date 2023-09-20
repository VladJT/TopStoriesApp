package jt.projects.topstoriesapp.repository

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

//В библиотеку можно внедрить перехватчики для изменения заголовков при помощи класса Interceptor из OkHttp.
// Сначала следует создать объект перехватчика и передать его в OkHttp, который в свою очередь следует явно подключить в
// Retrofit.Builder через метод client()
class BaseInterceptor private constructor() : Interceptor {
    private var responseCode: Int = 0

    companion object {
        val interceptor: BaseInterceptor
            get() = BaseInterceptor()
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val response = chain.proceed(chain.request())
        responseCode = response.code
        return response
    }

    fun getResponseCode(): ServerResponseStatusCode {
        var statusCode = ServerResponseStatusCode.UNDEFINED_ERROR
        when (responseCode / 100) {
            1 -> statusCode = ServerResponseStatusCode.INFO
            2 -> statusCode = ServerResponseStatusCode.SUCCESS
            3 -> statusCode = ServerResponseStatusCode.REDIRECTION
            4 -> statusCode = ServerResponseStatusCode.CLIENT_ERROR
            5 -> statusCode = ServerResponseStatusCode.SERVER_ERROR
        }
        return statusCode
    }

    enum class ServerResponseStatusCode {
        INFO,
        SUCCESS,
        REDIRECTION,
        CLIENT_ERROR,
        SERVER_ERROR,
        UNDEFINED_ERROR
    }

    /**
    В библиотеку можно внедрить перехватчики для изменения заголовков при помощи класса Interceptor из OkHttp.
    Сначала следует создать объект перехватчика и передать его в OkHttp, который в свою очередь следует явно подключить в
    Retrofit.Builder через метод client().
     */
    fun createOkHttpClient() =
        OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).build()
}
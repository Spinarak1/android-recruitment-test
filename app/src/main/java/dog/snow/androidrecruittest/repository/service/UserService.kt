package dog.snow.androidrecruittest.repository.service

import dog.snow.androidrecruittest.repository.model.RawUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @Headers("Content-Type: application/json")
    @GET("/users")
    fun userDownload(@Path("id") id: Int): Call<RawUser>
}
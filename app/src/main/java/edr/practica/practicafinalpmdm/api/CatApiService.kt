package edr.practica.practicafinalpmdm.api
// CatApiService.kt
import retrofit2.Call
import retrofit2.http.GET

interface CatApiService {
    @GET("images/search")
    suspend fun getRandomCatImage(): List<CatImage>
}

package edr.practica.practicafinalpmdm.api
import retrofit2.http.GET

interface CatApiService {
    @GET("images/search")
    suspend fun getRandomCatImage(): List<CatImage>
}

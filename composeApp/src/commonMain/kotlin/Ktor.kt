import io.ktor.client.HttpClient
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

object Ktor {
    private const val BASE_URL = "https://thronesapi.com/api/v2/"
    val client: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            url(BASE_URL)
        }
    }
}
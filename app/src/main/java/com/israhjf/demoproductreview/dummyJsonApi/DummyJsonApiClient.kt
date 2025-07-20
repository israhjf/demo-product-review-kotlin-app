package com.israhjf.demoproductreview.dummyJsonApi

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class DummyJsonApiClient {
//    val TAG = ApiClient::class.simpleName ?: "KtorClient"
//    private val BaseHost
//        get() = if (BuildConfig.DEBUG) "mpa-bff.monovodev.com" else "mpa-bff.monovocare.com"
//
//    private val client = HttpClient {
//
//        install(Auth) {
//            bearer {
//                sendWithoutRequest { request ->
//                    val host = request.url.host
//                    host.contains("api.radar") || host.contains("password-reset")
//                }
//                loadTokens {
//                    // Implement logic to retrieve the auth header and refresh token
//                    getAuthToken()
//                    BearerTokens(Prefs.getString(PreferencesConstants.ACCESS_TOKEN), "")
//                }
//                refreshTokens {
//                    // Implement logic to refresh the auth header using the refresh token
//                    getAuthToken()
//                    BearerTokens(Prefs.getString(PreferencesConstants.ACCESS_TOKEN), "")
//                }
//
//            }
//        }
//
//        install(Logging) {
//            logger = object : LoggerInterface {
//                override fun log(message: String) {
//                    Log.i(TAG, "log: $message")
//                    Logger.writeLog(TAG, "log: $message")
//                }
//            }
//            level = LogLevel.BODY
//        }
//
//        install(ContentNegotiation) {
//            json(Json {
//                isLenient = true
//                prettyPrint = true
//                ignoreUnknownKeys = true
//                coerceInputValues = true
//                encodeDefaults = true
//            })
//        }
//
////        defaultRequest {
////            url {
////                protocol = URLProtocol.HTTPS
////                host = BaseHost
////                encodedPath = "/api/v1/" // Base path
////                contentType(ContentType.Application.Json)
////            }
////        }
//
//        install(HttpTimeout) {
//            requestTimeoutMillis = 60_000
//            connectTimeoutMillis = 60_000
//            socketTimeoutMillis = 60_000
//        }
//
//
//        install(ResponseObserver) {
//
//            onResponse { response ->
//                Log.i("KtorApiClient", "response received -> ${response.bodyAsText()}")
//                Log.i(
//                    TAG,
//                    "request took -> ${response.responseTime.timestamp - response.requestTime.timestamp}ms"
//                )
//            }
//        }
//    }
//        .apply {
//            plugin(HttpSend).intercept { request ->
//                if (request.url.host.contains("api.radar")) {
//                    request.headers.remove("Authorization")
//                    request.headers.append("Authorization", RadarApiManager.key ?: "")
//                }
//                execute(request)
//
//            }
//        }
//
//    suspend inline fun <reified T> sendRequest(
//        holder: RequestHolder<T>,
//        address: String? = null
//    ) = makeNetworkCall(holder, address) { return@makeNetworkCall it.body<T>() }
//
//    suspend fun <T> makeNetworkCall(
//        holder: RequestHolder<T>,
//        address: String?,
//        secure: Boolean = true,
//        getBody: suspend (HttpResponse) -> T
//    ) = withContext(Dispatchers.IO) {
//
//        val url = Url(
//            builder = URLBuilder(
//                protocol = URLProtocol.createOrDefault(if (secure) "https" else "http"),
//                host = address ?: BaseHost,
//                pathSegments = holder.path.takeIf { it.isNotBlank() }?.split("/") ?: emptyList(),
//            )
//        )
//
////        val client = httpClient
//
//
//        val webResult = try {
//
//            val response = when (holder.method) {
//                RestMethod.GET -> {
//                    client.get(url) {
//                        setRequest(holder)
//                        setQuery(holder)
//                    }
//                }
//
//                RestMethod.POST -> {
//                    client.post(url) {
//                        setRequest(holder)
//                        setBody(holder.body)
//                    }
//                }
//
//                RestMethod.PUT -> {
//                    client.put(url) {
//                        setRequest(holder)
//                        setBody(holder.body)
//                    }
//                }
//
//                RestMethod.DELETE -> {
//                    client.delete(url) {
//                        setRequest(holder)
//                        setBody(holder.body)
//                    }
//                }
//            }
//
//            if (response.status.isSuccess()) ApiResponse.Success(
//                getBody(response),
//                response.headers
//            )
//            else ApiResponse.Error(response.status.description, response.status.value)
//
//        } catch (i: Exception) {
//            Log.i("KtorApiClient", "exception occurred on ${holder.name} -> ${i.message}")
//            ApiResponse.Error(message = i.message ?: "Unknown error occurred",
//                code = ApiDao.NO_NETWORK_CODE)
//        }
//
//
//
//        return@withContext webResult
//    }
//
//    private fun <T> HttpRequestBuilder.setRequest(
//        holder: RequestHolder<T>,
//    ) {
//        setHeaders(holder)
//        setTimeOut(holder)
//    }
//
//    private fun <T> HttpRequestBuilder.setQuery(holder: RequestHolder<T>) {
//        url {
//            if (holder.parameters.isNotEmpty()) {
//                holder.parameters.forEach { (key, value) ->
//                    parameters.append(key, value)
//                }
//            }
//            if (holder.query.isNotEmpty()) {
//                holder.query.split("&").forEach { parameter ->
//                    val (key, value) = parameter.split("=")
//                    parameters.append(key, value)
//                }
//            }
//        }
//    }
//
//    private fun <T> HttpRequestBuilder.setHeaders(holder: RequestHolder<T>) {
//        holder.headers.entries.forEach {
//            if (!headers.names().contains(it.key) ) {
//                headers.append(it.key, it.value)
//            }
//        }
//        if (!headers.names().contains("Accept")) {
//            headers.append("Accept", "application/json")
//        }
//    }
//
//    private fun <T> HttpRequestBuilder.setTimeOut(holder: RequestHolder<T>) {
//        holder.timeout?.let {
//            timeout {
//                requestTimeoutMillis = it
//            }
//        }
//    }
//
//    private suspend fun getAuthToken() {
//        val token =  try {
//            val mUser =
//                FirebaseAuth.getInstance().currentUser ?: throw Exception("Current user was null")
//            mUser.getIdToken(true)
//                .await().token!!
//        } catch (e: Exception) {
//            Logger.writeLog(TAG, "Failed to retrieve user token - ${e.message}")
//            ""
//        }
//
//        Prefs.putString(PreferencesConstants.ACCESS_TOKEN, token)
//    }
}
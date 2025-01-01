package com.blog.blog_service.product.domain.utils

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request

@Serializable
data class ImageData(val download_url: String)

fun fetchImages(): List<String> {
    val url = "https://picsum.photos/v2/list?page=1&limit=40"
    val client = OkHttpClient()
    val request = Request.Builder().url(url).build()

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) throw Exception("Failed to fetch images: ${response.code}")

        val json = response.body?.string() ?: throw Exception("Response body is null")
        val jsonParser = Json { ignoreUnknownKeys = true } // Ignore unknown keys
        val imageList = jsonParser.decodeFromString<List<ImageData>>(json)
        return imageList.map { it.download_url }
    }
}

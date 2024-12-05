package com.example.newsflash.DataModel

data class Article(
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val type:String?=null
) {

}

data class Source(
    val id: String? = null,
    val name: String? = null
)


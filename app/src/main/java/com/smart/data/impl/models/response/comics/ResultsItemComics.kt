package com.smart.data.impl.models.response.comics

import com.google.gson.annotations.SerializedName

data class ResultsItemComics(
    @SerializedName("creators")
    val creators: Creators,
    @SerializedName("issueNumber")
    val issueNumber: String = "",
    @SerializedName("isbn")
    val isbn: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("variants")
    val variants: List<VariantsItem>?,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("diamondCode")
    val diamondCode: String = "",
    @SerializedName("characters")
    val characters: Characters,
    @SerializedName("urls")
    val urls: List<UrlsItem>?,
    @SerializedName("ean")
    val ean: String = "",
    @SerializedName("collections")
    val collections: List<CollectionsItem>?,
    @SerializedName("modified")
    val modified: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("prices")
    val prices: List<PricesItem>?,
    @SerializedName("events")
    val events: Events,
    @SerializedName("collectedIssues")
    val collectedIssues: List<CollectedIssuesItem>?,
    @SerializedName("pageCount")
    val pageCount: String = "",
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("images")
    val images: List<ImagesItem>?,
    @SerializedName("stories")
    val stories: Stories,
    @SerializedName("textObjects")
    val textObjects: List<TextObjectsItem>?,
    @SerializedName("digitalId")
    val digitalId: String = "",
    @SerializedName("format")
    val format: String = "",
    @SerializedName("upc")
    val upc: String = "",
    @SerializedName("dates")
    val dates: List<DatesItem>?,
    @SerializedName("resourceURI")
    val resourceURI: String = "",
    @SerializedName("variantDescription")
    val variantDescription: String = "",
    @SerializedName("issn")
    val issn: String = "",
    @SerializedName("series")
    val series: Series,
)
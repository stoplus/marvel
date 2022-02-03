package com.smart.presentation.impl.detailsScreen.model

data class DetailsPresentModel(
    val link: String = "",
    val description: String = "",
    val comics: List<DetailsListModel> = listOf(),
    val events: List<DetailsListModel> = listOf(),
    val series: List<DetailsListModel> = listOf(),
    val stories: List<DetailsListModel> = listOf(),
)

data class DetailsListModel(
    val id: String,
    val link: String = "",
    val name: String = "",
)
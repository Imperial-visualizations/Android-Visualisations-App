package com.example.imperialvisualisationsandroid

class DataModel(val Visualisations: List<Visualisation>)

class Visualisation(val id: Int, val name: String, val info: String, val url_name: String, val tags: String, val imageURL: String, val gifURL: String)
package br.unisanta.appsanta.model

data class Produtos(
    val nome: String = "",
    val pizza: Int = 0,
    val crepe: Int = 0,
    val hamburguer: Int = 0,
    var email : String = "",
    var status : String = "",
    var id : Int =0
    )
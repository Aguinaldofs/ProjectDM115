package br.com.posinatel.projectdm115.network;

data class Product(
    var id: Long = 0,
    var name: String,
    var description: String,
    var code: String,
    var price: Double
)

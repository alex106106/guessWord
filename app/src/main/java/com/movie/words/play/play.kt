package com.movie.words.play

import java.io.File
import java.util.*


fun generateRandomWord(): String {
    val dictionary = listOf(
        "abrazo", "abuela", "aceituna", "acera", "actriz", "agua", "aguacate", "ahorro", "aire",
        "albañil", "alcoba", "alfombra", "alimento", "almohada", "alto", "amigo", "amor", "ancla",
        "animal", "anillo", "antebrazo", "aparato", "apellido", "apio", "aprendiz", "arco", "arena",
        "arpa", "arroz", "arte", "asa", "asado", "asiento", "atleta", "avión", "azúcar", "bailarín",
        "bala", "banco", "bandera", "baño", "bar", "barba", "barco", "barriga", "bebé", "bebida",
        "bello", "beso", "bici", "bicicleta", "bikini", "billete", "blusa", "boca", "bolsa", "bomba",
        "bote", "botella", "botón", "brazo", "broma", "bruja", "bruto", "bueno", "bufanda", "bufón",
        "bulevar", "burro", "bus", "caballo", "cabeza", "cabra", "cacao", "cactus", "café", "caja",
        "cajón", "calabaza", "caldo", "calendario", "calor", "cama", "camarero", "camino", "camisa",
        "campamento", "campana", "canguro", "canoa", "cansado", "canto", "capa", "capital", "capitán",
        "cara", "caracol", "carne", "carpeta", "carro", "carta", "casa", "casco", "castillo", "catedral",
        "causa", "cavidad", "cebolla", "cedro", "celeste", "celular", "ceniza", "centro", "cerdo",
        "cerebro", "cerro", "cesta", "chaleco", "champán", "chancla", "chocolate", "chorro", "chuleta",
        "churro", "ciclismo", "ciego", "cielo", "cinco", "circuito", "ciruela", "clase", "clavel",
        "cliente", "clima", "clínica", "cobertura", "coco", "codo", "cohete", "cola", "colección",
        "colina", "collar", "colmena", "color", "cometa", "comida", "compás", "computadora", "comunidad",
        "conejo", "confianza", "congreso", "conversación", "corazón", "corbata", "corona", "corredor")
    val words = dictionary
    val randomIndex = Random().nextInt(words.size)
    return words[randomIndex]
}
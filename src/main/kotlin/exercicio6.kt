// Classe abstrata que representa uma peça
abstract class Peca(val marca: String, val modelo: String) {
    abstract fun retirada()
}

// Classe que representa o GuardaVolumes
class GuardaVolumes {
    private val pecasMap = mutableMapOf<Int, ArrayList<Peca>>()
    private var contador = 0

    fun guardarPecas(listaDePeca: ArrayList<Peca>): Int {
        val numeroIdentificacao = contador++
        pecasMap[numeroIdentificacao] = listaDePeca
        return numeroIdentificacao
    }

    fun mostrarPecas() {
        if (pecasMap.isEmpty()) {
            println("Guarda-volumes vazio.")
        } else {
            println("Pecas no guarda-volumes:")
            pecasMap.forEach { (numero, listaPecas) ->
                println("Número de Identificação: $numero")
                listaPecas.forEachIndexed { index, peca ->
                    println("Peça ${index + 1}: Marca: ${peca.marca}, Modelo: ${peca.modelo}")
                }
            }
        }
    }

    fun mostrarPecas(numero: Int) {
        val listaPecas = pecasMap[numero]
        if (listaPecas != null) {
            println("Pecas associadas ao número de identificação $numero:")
            listaPecas.forEachIndexed { index, peca ->
                println("Peça ${index + 1}: Marca: ${peca.marca}, Modelo: ${peca.modelo}")
            }
        } else {
            println("Número de identificação não encontrado.")
        }
    }

    fun devolverPecas(numero: Int) {
        val listaPecas = pecasMap.remove(numero)
        if (listaPecas != null) {
            println("Pecas associadas ao número de identificação $numero foram devolvidas.")
        } else {
            println("Número de identificação não encontrado.")
        }
    }
}

fun main() {
    val guardaVolumes = GuardaVolumes()

    // Cenário: Alguém guarda duas peças e depois as retira
    val listaPecas = arrayListOf(
        Vestuario("Nike", "Camiseta"),
        Acessorio("Adidas", "Boné")
    )

    val numeroIdentificacao = guardaVolumes.guardarPecas(listaPecas)
    println("Número de identificação gerado: $numeroIdentificacao")

    guardaVolumes.mostrarPecas()
    guardaVolumes.mostrarPecas(numeroIdentificacao)

    guardaVolumes.devolverPecas(numeroIdentificacao)

    guardaVolumes.mostrarPecas()
    guardaVolumes.mostrarPecas(numeroIdentificacao)
}

// Subclasses que representam tipos específicos de peças
class Vestuario(marca: String, modelo: String) : Peca(marca, modelo) {
    override fun retirada() {
        println("Retirando vestuário - Marca: $marca, Modelo: $modelo")
    }
}

class Acessorio(marca: String, modelo: String) : Peca(marca, modelo) {
    override fun retirada() {
        println("Retirando acessório - Marca: $marca, Modelo: $modelo")
    }
}

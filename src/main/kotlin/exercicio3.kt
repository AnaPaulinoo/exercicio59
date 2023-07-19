import java.util.*

fun main() {
    val dictionary = Dictionary()

    while (true) {
        println("Escolha uma opção:")
        println("1. Adicionar termo")
        println("2. Procurar termo")
        println("3. Listar todos os termos em ordem alfabética")
        println("4. Sair")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                println("Digite o termo:")
                val termo = readLine() ?: continue
                println("Digite a definição:")
                val definicao = readLine() ?: continue
                dictionary.adicionarTermo(termo, definicao)
            }
            2 -> {
                println("Digite o termo que deseja procurar:")
                val termo = readLine() ?: continue
                val definicao = dictionary.procurarTermo(termo)
                if (definicao != null) {
                    println("Definição: $definicao")
                } else {
                    println("Termo não encontrado.")
                }
            }
            3 -> {
                println("Listando todos os termos em ordem alfabética:")
                dictionary.listarTermosOrdenados().forEach { (termo, definicao) ->
                    println("$termo: $definicao")
                }
            }
            4 -> {
                println("Saindo...")
                return
            }
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

class Dictionary {
    private val terms = TreeMap<String, String>()

    fun adicionarTermo(termo: String, definicao: String) {
        terms[termo] = definicao
        println("Termo adicionado com sucesso.")
    }

    fun procurarTermo(termo: String): String? {
        return terms[termo]
    }

    fun listarTermosOrdenados(): Map<String, String> {
        return terms
    }
}


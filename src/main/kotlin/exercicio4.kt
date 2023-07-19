fun main() {
    val habitantes = mutableListOf<Habitante>()

    while (true) {
        println("Escolha uma opção:")
        println("1. Cadastrar habitante e profissão")
        println("2. Mostrar informações de um habitante")
        println("3. Mostrar salários cadastrados")
        println("4. Calcular imposto de cada mês")
        println("5. Sair")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                println("Digite o nome do habitante:")
                val nome = readLine() ?: continue
                println("Digite a profissão do habitante:")
                val profissao = readLine() ?: continue
                val habitante = Habitante(nome, profissao)
                habitantes.add(habitante)
                println("Habitante cadastrado com sucesso.")
            }
            2 -> {
                println("Digite o índice do habitante:")
                val index = readLine()?.toIntOrNull() ?: continue
                if (index >= 0 && index < habitantes.size) {
                    val habitante = habitantes[index]
                    println("Nome: ${habitante.nome}, Profissão: ${habitante.profissao}")
                } else {
                    println("Índice inválido.")
                }
            }
            3 -> {
                println("Salários cadastrados:")
                println("Digite o índice do habitante:")
                val index = readLine()?.toIntOrNull() ?: continue
                if (index >= 0 && index < habitantes.size) {
                    val habitante = habitantes[index]
                    println("Salários do habitante ${habitante.nome}:")
                    for ((i, salario) in habitante.salarios.withIndex()) {
                        println("Mês ${i + 1}: R$ $salario")
                    }
                } else {
                    println("Índice inválido.")
                }
            }
            4 -> {
                println("Digite o índice do habitante:")
                val index = readLine()?.toIntOrNull() ?: continue
                if (index >= 0 && index < habitantes.size) {
                    val habitante = habitantes[index]
                    println("Imposto de cada mês para o habitante ${habitante.nome}:")
                    for ((i, salario) in habitante.salarios.withIndex()) {
                        val imposto = calcularImposto(salario)
                        println("Mês ${i + 1}: R$ $imposto")
                    }
                } else {
                    println("Índice inválido.")
                }
            }
            5 -> {
                println("Saindo...")
                return
            }
            else -> println("Opção inválida, tente novamente.")
        }
    }
}
data class Habitante(val nome: String, val profissao: String) {
    val salarios = DoubleArray(12)
}

fun calcularImposto(salario: Double): Double {
    return when {
        salario <= 2000.0 -> 0.0
        salario <= 3000.0 -> salario * 0.08
        salario <= 4500.0 -> salario * 0.18
        else -> salario * 0.28
    }
}
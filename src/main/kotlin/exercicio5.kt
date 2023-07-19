import java.time.LocalDate
import java.time.Period

data class Aluno(val matricula: Int, val nome: String, val dataNascimento: LocalDate, val sexo: String)

class Turma {
    private val alunos = mutableListOf<Aluno>()

    fun cadastrarAluno(aluno: Aluno) {
        if (alunos.none { it.matricula == aluno.matricula }) {
            alunos.add(aluno)
            println("Aluno cadastrado com sucesso.")
        } else {
            println("Matrícula já cadastrada.")
        }
    }

    fun listarAlunos() {
        if (alunos.isEmpty()) {
            println("Não há alunos cadastrados.")
        } else {
            println("Lista de alunos:")
            alunos.forEach { println("Matrícula: ${it.matricula}, Nome: ${it.nome}, Sexo: ${it.sexo}, Data de Nascimento: ${it.dataNascimento}") }
        }
    }

    fun listarAlunosPorSobrenome(sobrenome: String) {
        val alunosComSobrenome = alunos.filter { it.nome.endsWith(sobrenome, true) }
        if (alunosComSobrenome.isEmpty()) {
            println("Nenhum aluno encontrado com o sobrenome informado.")
        } else {
            println("Alunos com o sobrenome '$sobrenome':")
            alunosComSobrenome.forEach { println("Matrícula: ${it.matricula}, Nome: ${it.nome}, Sexo: ${it.sexo}, Data de Nascimento: ${it.dataNascimento}") }
        }
    }

    fun listarAlunoMaisIdoso() {
        val alunoMaisIdoso = alunos.maxByOrNull { it.dataNascimento }
        if (alunoMaisIdoso != null) {
            println("Aluno mais idoso:")
            println("Matrícula: ${alunoMaisIdoso.matricula}, Nome: ${alunoMaisIdoso.nome}, Sexo: ${alunoMaisIdoso.sexo}, Data de Nascimento: ${alunoMaisIdoso.dataNascimento}")
        } else {
            println("Não há alunos cadastrados.")
        }
    }

    fun calcularMediaIdadeAlunos(): Double {
        if (alunos.isEmpty()) return 0.0
        val dataAtual = LocalDate.now()
        val somaIdades = alunos.sumBy { Period.between(it.dataNascimento, dataAtual).years }
        return somaIdades.toDouble() / alunos.size
    }

    fun atualizarAluno(matricula: Int, novoAluno: Aluno) {
        val index = alunos.indexOfFirst { it.matricula == matricula }
        if (index != -1) {
            alunos[index] = novoAluno
            println("Aluno atualizado com sucesso.")
        } else {
            println("Aluno não encontrado.")
        }
    }

    fun removerAluno(matricula: Int) {
        val removido = alunos.removeIf { it.matricula == matricula }
        if (removido) {
            println("Aluno removido com sucesso.")
        } else {
            println("Aluno não encontrado.")
        }
    }
}

fun main() {
    val turma = Turma()

    while (true) {
        println("\nEscolha uma opção:")
        println("1. Cadastrar aluno")
        println("2. Listar todos os alunos")
        println("3. Listar alunos com sobrenome informado")
        println("4. Listar aluno mais idoso")
        println("5. Listar média de idade dos alunos")
        println("6. Atualizar dados de um aluno")
        println("7. Remover dados de um aluno")
        println("8. Sair")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                println("Digite a matrícula do aluno:")
                val matricula = readLine()?.toIntOrNull() ?: continue
                println("Digite o nome do aluno:")
                val nome = readLine() ?: continue
                println("Digite a data de nascimento do aluno (formato: AAAA-MM-DD):")
                val dataNascimento = LocalDate.parse(readLine() ?: continue)
                println("Digite o sexo do aluno:")
                val sexo = readLine() ?: continue

                val aluno = Aluno(matricula, nome, dataNascimento, sexo)
                turma.cadastrarAluno(aluno)
            }
            2 -> turma.listarAlunos()
            3 -> {
                println("Digite o sobrenome:")
                val sobrenome = readLine() ?: continue
                turma.listarAlunosPorSobrenome(sobrenome)
            }
            4 -> turma.listarAlunoMaisIdoso()
            5 -> {
                val mediaIdade = turma.calcularMediaIdadeAlunos()
                println("Média de idade dos alunos: $mediaIdade")
            }
            6 -> {
                println("Digite a matrícula do aluno que deseja atualizar:")
                val matricula = readLine()?.toIntOrNull() ?: continue
                println("Digite o novo nome do aluno:")
                val nome = readLine() ?: continue
                println("Digite a nova data de nascimento do aluno (formato: AAAA-MM-DD):")
                val dataNascimento = LocalDate.parse(readLine() ?: continue)
                println("Digite o novo sexo do aluno:")
                val sexo = readLine() ?: continue

                val novoAluno = Aluno(matricula, nome, dataNascimento, sexo)
                turma.atualizarAluno(matricula, novoAluno)
            }
            7 -> {
                println("Digite a matrícula do aluno que deseja remover:")
                val matricula = readLine()?.toIntOrNull() ?: continue
                turma.removerAluno(matricula)
            }
            8 -> {
                println("Saindo...")
                return
            }
            else -> println("Opção inválida, tente novamente.")
        }
    }
}

import kotlinx.coroutines.*

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

//Adicionei nome ao usuário;
data class Usuario(val nome:String)

//Coloquei o nome como val ao invés de var e tirei o valor padrão de duração do curso;
data class ConteudoEducacional(val nome: String, val duracao: Int)

//Mudei de data class para class e mudei conteudos de var para val;
class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    
    //Adicionei vararg em usuário para receber vários usuários;
    fun matricular(vararg usuario: Usuario) {
        
        //Enquanto tiver usuário, adicione o usuário em inscritos;
        usuario.forEach{
           inscritos.add(it) 
        }
        runBlocking{ visualizar() }
        // TODO("Utilize o parâmetro $usuario para simular uma matrícula (usar a lista de $inscritos).")
    }
    
    //Criei uma função para exibir o curso e os alunos matriculados
    suspend fun visualizar() = coroutineScope {
        var duracaoBootCamp = 0
        launch{
            delay(1000L)
            println("Os alunos ${inscritos.map{it.nome}.joinToString()} estão matriculados no curso.")
        }
        val cursos = conteudos.forEach{ duracaoBootCamp += it.duracao }
        println("A formação $nome tem $duracaoBootCamp horas e tem os cursos de: ${conteudos.map{it.nome}.joinToString()}")
    }
}

fun main() {
    // TODO("Analise as classes modeladas para este domínio de aplicação e pense em formas de evoluí-las.")
    //TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
    
    val conteudos:List<ConteudoEducacional> = listOf(ConteudoEducacional("POO", 8),
                          							ConteudoEducacional("Arquiteturas", 4),
                          							ConteudoEducacional("Avançando com Kotlin", 3))
    
       Formacao("Kotlin Experience", conteudos).matricular(Usuario("Pedro"),
                                                        Usuario("Lucas"),
                                                        Usuario("Cris"),
                                                        Usuario("Tulio"))  
}
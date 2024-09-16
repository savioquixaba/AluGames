package br.com.alura.alugames.principal

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import br.com.alura.alugames.modelo.Jogo
import br.com.alura.alugames.servicos.ConsumoApi
import java.util.Scanner

fun main() {
    val leitura = Scanner(System.`in`)
    println("Digite um Código de jogo para buscar:")
    val busca = leitura.nextLine()

    val buscaApi = ConsumoApi()
    val infomacaojogo = buscaApi.buscaJogo(busca)

    var meuJogo: Jogo? = null

    val resultado = runCatching {
         meuJogo = Jogo(
             infomacaojogo.info.title,
             infomacaojogo.info.thumb
         )


    }

    resultado.onFailure {
        println("Jogo inexistente. Tente outro id.")
    }

    resultado.onSuccess {
        println("deseja inserir uma descrição personalizada? S/N")
        val  opcao = leitura.nextLine()
        if (opcao.equals("s",ignoreCase = true)) {
            println("insira descrição personalizada para o jogo:")
            val descricaoPersonalizada = leitura.nextLine()
            meuJogo?.descricao = descricaoPersonalizada
        }else{
            meuJogo?.descricao = meuJogo.titulo
        }
        println(meuJogo)
    }
    resultado.onSuccess {
        println("Busca Realizada com Sucesso!")
    }


}


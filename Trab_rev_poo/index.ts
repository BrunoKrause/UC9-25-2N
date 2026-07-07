import { Locadora } from "./class/locadora";
import readlineSync from "readline-sync";

export const dataAtual = "03/07/2026"
const GameZone = new Locadora("GameZone");

function menu() {

    let opcao = -1;

    while (opcao !== 0) {

        console.clear();

        console.log(`
==================================
          GAME ZONE
==================================
1 - Adicionar Jogo
2 - Adicionar Cliente
3 - Adicionar Locação
4 - Listar Jogos
5 - Listar Clientes
6 - Listar Locações
0 - Sair
==================================
`);

        opcao = Number(
            readlineSync.question("Escolha uma opcao: ")
        );


        switch (opcao) {

            case 1:
                adicionarJogo();
                break;


            case 2:
                adicionarCliente();
                break;


            case 3:
                adicionarLocacao();
                break;


            case 4:
                GameZone.listarJogos();
                esperar();
                break;


            case 5:
                GameZone.listarClientes();
                esperar();
                break;


            case 6:
                GameZone.listarLocacoes();
                esperar();
                break;


            case 0:
                console.log(`
Sistema encerrado.
Obrigado por utilizar a GameZone!
`);
                break;


            default:
                console.log(`
Opção inválida!
`);
                esperar();
        }
    }
}



function adicionarJogo() {

    console.clear();

    console.log(`
========== ADICIONAR JOGO ==========
`);

    const nome = readlineSync.question("Nome do jogo: ");

    const plataforma = readlineSync.question("Plataforma: ");

    const genero = readlineSync.question("Genero: ");

    const valorDiaria = Number(
        readlineSync.question("Valor da diaria: ")
    );

    GameZone.adicionarJogo(nome, plataforma, genero, valorDiaria);
    esperar();
}



function adicionarCliente() {

    console.clear();

    console.log(`
========== ADICIONAR CLIENTE ==========
`);


    const nome = readlineSync.question(
        "Nome: "
    );

    const idade = Number(
        readlineSync.question(
            "Idade: "
        )
    );

    const telefone = readlineSync.question(
        "Telefone: "
    );

    const email = readlineSync.question(
        "Email: "
    );

    GameZone.adicionarCliente(nome, idade, telefone, email);
    esperar();
}



function adicionarLocacao() {

   console.log('Vamos escolher o cliente: ')
   GameZone.listarClientes()
   let idCliente = readlineSync.questionInt("Qual o cliente que esta alugando? ") - 1

   console.log('Vamos escolher agora o jogo: ')
   GameZone.listarJogos()
   let idJogo = readlineSync.questionInt("Qual o jogo que será alugado? ") - 1

   if (!GameZone.jogos[idJogo].getDisponivel()) {
    console.log("\nEste jogo já está alugado, tente outro")
    let continuar = readlineSync.question("Press enter to continue...")
    adicionarLocacao()
   }

   let dataLocacao = readlineSync.question("Qual a data da locação? ")
   let dataDevolucao = readlineSync.question("Qual a data da devolução? ")

   GameZone.registrarLocacao(GameZone.clientes[idCliente], GameZone.jogos[idJogo], dataLocacao, dataDevolucao)

    esperar();
}



function esperar() {

    readlineSync.question(
        "\nPressione ENTER para continuar..."
    );
}



menu();
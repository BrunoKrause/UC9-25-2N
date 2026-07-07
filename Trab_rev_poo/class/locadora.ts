import { Cliente } from "./clientes";
import { Jogo } from "./jogos";
import { Locacao } from "./locacoes";
import { Banco } from "../banco/banco";
import { dataAtual } from "..";

export class Locadora {
    private nome: string;
    public jogos: Jogo[];
    public clientes: Cliente[];
    private locacoes: Locacao[];

    constructor(nome: string) {
        this.nome = nome;
        this.jogos = Banco.jogos;
        this.clientes = Banco.clientes;
        this.locacoes = Banco.locacoes;
    }

    public adicionarJogo(nome: string, plataforma: string, genero: string, valorDiaria: number){
        this.jogos.push(new Jogo(nome, plataforma, genero, valorDiaria));
        console.log(`O Jogo ${nome} foi cadastrado com sucesso!`)
        return;
    }

    public adicionarCliente(nome: string, idade: number, telefone: string, email: string) {
        this.clientes.push(new Cliente(nome, idade, telefone, email));
        console.log(`Cliente ${nome} adicionado com sucesso!`)
        return;
    }

    public registrarLocacao(cliente: Cliente, jogo: Jogo, dataLocacao: string, dataDevolucao: string) {
        if (!jogo.getDisponivel()){
            console.log("Este jogo já está alugado.")
            return;
        }

        this.locacoes.push(new Locacao(jogo, cliente, dataLocacao, dataDevolucao))
        jogo.setDisponivel(false);

        console.log("Locação Realizada com sucesso!");
    }
    public listarJogos(): void {

        let counter = 1;
       
        for (const jogo of this.jogos) {
    
            console.log(`
    ${counter} - ${jogo.getNome()}
    Plataforma: ${jogo.getPlataforma()}
    Gênero: ${jogo.getGenero()}
    Valor diária: R$ ${jogo.getValorDiaria()}
    Status: ${jogo.getDisponivel() ? "Disponível" : "Alugado"}
    -------------------------------
    `);
    
            counter++;
        }
    }

    public listarClientes() {
        let counter = 1
        for( let Cliente of this.clientes) {
            console.log(`
    ${counter}. ${Cliente.getNome()}
            `)
            counter++
        }
    }

    public verificarAtraso(dataAtual: string, dataDevolucao: string) {

        const diaAtual = parseInt(dataAtual.split("/")[0]);
        const diaDevolucao = parseInt(dataDevolucao.split("/")[0]);

        if (diaAtual > diaDevolucao) {
            return "Situação: Locação Atrasada!"
        } else {
            return "Situação: Locação dentro do Prazo."
        }
    }

    public calcularValorPrevisto(dataLocacao: string, dataDevolucao: string, valorDiaria: number): number {
        
        const diaLocacao = parseInt(dataLocacao.split("/")[0]);
        const diaDevolucao = parseInt(dataDevolucao.split("/")[0]);

        const dias = diaDevolucao - diaLocacao;

        return dias * valorDiaria;
    }
    
    public listarLocacoes() {
        let counter = 1
        for( let Locacao of this.locacoes) {
            console.log(`
        Resumo da Locação
    ${counter}.
    Jogo locado: ${Locacao.getJogo().getNome()}
    Plataforma: ${Locacao.getJogo().getPlataforma()}
    Gênero: ${Locacao.getJogo().getGenero()}
    Cliente: ${Locacao.getCliente().getNome()}
    Data da Locação: ${Locacao.getDataLocacao()}
    Previsão de devolução: ${Locacao.getDataDevolucao()}
    Valor previsto: ${this.calcularValorPrevisto(Locacao.getDataLocacao(), Locacao.getDataDevolucao(), Locacao.getJogo().getValorDiaria())}
    ${this.verificarAtraso(dataAtual, Locacao.getDataDevolucao())}
            `)
            counter++
        }
    }
}
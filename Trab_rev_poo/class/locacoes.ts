import { Cliente } from "./clientes";
import { Jogo } from "./jogos";

export class Locacao {
    private jogo: Jogo;
    private cliente: Cliente;
    private dataLocacao: string;
    private dataDevolucao: string;

    constructor(jogo: Jogo, cliente: Cliente, dataLocacao: string, dataDevolucao: string) {
        this.jogo = jogo;
        this.cliente = cliente;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
    }

    public getJogo(): Jogo {
        return this.jogo;
    }

    public setJogo(jogo: Jogo): void {
        this.jogo = jogo;
    }

    public getCliente(): Cliente {
        return this.cliente;
    }

    public setCliente(cliente: Cliente): void {
        this.cliente = cliente;
    }

    public getDataLocacao(): string {
        return this.dataLocacao;
    }

    public setDataLocacao(dataLocacao: string): void {
        this.dataLocacao = dataLocacao;
    }

    public getDataDevolucao(): string {
        return this.dataDevolucao;
    }

    public setDataDevolucao(dataDevolucao: string): void {
        this.dataDevolucao = dataDevolucao;
    }
}


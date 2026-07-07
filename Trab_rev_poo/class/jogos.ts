export class Jogo {
    private nome: string;
    private plataforma: string;
    private genero: string;
    private valorDiaria: number;
    private disponivel: boolean;

    constructor(nome: string, plataforma: string, genero: string, valorDiaria: number) {
        this.nome = nome;
        this.plataforma = plataforma;
        this.genero = genero;
        this.valorDiaria = valorDiaria;
        this.disponivel = true;
    }

    public getNome(): string {
        return this.nome;
    }
    
    public setNome(nome: string): void {
        this.nome = nome;
    }
    
    public getPlataforma(): string {
        return this.plataforma;
    }
    
    public setPlataforma(plataforma: string): void {
        this.plataforma = plataforma;
    }
    
    public getGenero(): string {
        return this.genero;
    }
    
    public setGenero(genero: string): void {
        this.genero = genero;
    }
    
    public getValorDiaria(): number {
        return this.valorDiaria;
    }
    
    public setValorDiaria(valorDiaria: number): void {
        this.valorDiaria = valorDiaria;
    }
    
    public getDisponivel(): boolean {
        return this.disponivel;
    }
    
    public setDisponivel(disponivel: boolean): void {
        this.disponivel = disponivel;
    }
}
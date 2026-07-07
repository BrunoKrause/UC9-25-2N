export class Cliente {
    private nome: string;
    private idade: number;
    private telefone: string;
    private email: string;

    constructor (nome: string, idade: number, telefone: string, email: string) {
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.email = email;
    }
    public getNome(): string {
        return this.nome;
    }
    
    public setNome(nome: string): void {
        this.nome = nome;
    }
    
    public getIdade(): number {
        return this.idade;
    }
    
    public setIdade(idade: number): void {
        this.idade = idade;
    }
    
    public getTelefone(): string {
        return this.telefone;
    }
    
    public setTelefone(telefone: string): void {
        this.telefone = telefone;
    }
    
    public getEmail(): string {
        return this.email;
    }
    
    public setEmail(email: string): void {
        this.email = email;
    }
}
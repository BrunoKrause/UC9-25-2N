import { Cliente } from "../class/clientes";
import { Jogo } from "../class/jogos";
import { Locacao } from "../class/locacoes";

export class Banco {
    public static clientes: Cliente[] = [];
    public static jogos: Jogo[] = [];
    public static locacoes: Locacao[] = [];
}

Banco.clientes.push(
    new Cliente("Bruno Krause", 22, "(51) 99999-1111", "bruno@email.com"),
    new Cliente("Maria Oliveira", 19, "(51) 99999-2222", "maria@email.com"),
    new Cliente("Lucas Ferreira", 25, "(51) 99999-3333", "lucas@email.com"),
    new Cliente("Ana Souza", 20, "(51) 99999-4444", "ana@email.com"),
    new Cliente("Pedro Henrique", 28, "(51) 99999-5555", "pedro@email.com"),
    new Cliente("Fernanda Costa", 24, "(51) 99999-6666", "fernanda@email.com"),
    new Cliente("João Gabriel", 18, "(51) 99999-7777", "joao@email.com"),
    new Cliente("Camila Martins", 30, "(51) 99999-8888", "camila@email.com"),
    new Cliente("Rafael Almeida", 27, "(51) 99999-9999", "rafael@email.com"),
    new Cliente("Juliana Ribeiro", 21, "(51) 98888-1111", "juliana@email.com"),
    new Cliente("Carlos Eduardo", 31, "(51) 98888-2222", "carlos@email.com"),
    new Cliente("Patrícia Gomes", 26, "(51) 98888-3333", "patricia@email.com"),
    new Cliente("Leonardo Silva", 29, "(51) 98888-4444", "leonardo@email.com"),
    new Cliente("Isabela Rocha", 23, "(51) 98888-5555", "isabela@email.com"),
    new Cliente("Gustavo Lima", 34, "(51) 98888-6666", "gustavo@email.com"),
    new Cliente("Larissa Moraes", 20, "(51) 98888-7777", "larissa@email.com"),
    new Cliente("Vinícius Pereira", 27, "(51) 98888-8888", "vinicius@email.com"),
    new Cliente("Bianca Fernandes", 22, "(51) 98888-9999", "bianca@email.com"),
    new Cliente("Eduardo Nunes", 33, "(51) 97777-1111", "eduardo@email.com"),
    new Cliente("Natália Lopes", 24, "(51) 97777-2222", "natalia@email.com")
);

Banco.jogos.push(
    new Jogo("God of War Ragnarok", "PS5", "Ação", 18.90),
    new Jogo("Minecraft", "PC", "Sandbox", 10.00),
    new Jogo("The Legend of Zelda: Tears of the Kingdom", "Nintendo Switch", "Aventura", 20.00),
    new Jogo("EA Sports FC 26", "Xbox Series X", "Esporte", 15.00),
    new Jogo("Elden Ring", "PC", "RPG", 17.50),
    new Jogo("Mario Kart 8 Deluxe", "Nintendo Switch", "Corrida", 12.00),
    new Jogo("Red Dead Redemption 2", "PC", "Aventura", 19.90),
    new Jogo("GTA V", "PS5", "Ação", 16.00),
    new Jogo("The Witcher 3", "Xbox Series X", "RPG", 14.50),
    new Jogo("Hogwarts Legacy", "PS5", "RPG", 18.00),
    new Jogo("Resident Evil 4 Remake", "PC", "Terror", 17.00),
    new Jogo("Forza Horizon 5", "Xbox Series X", "Corrida", 13.90),
    new Jogo("Spider-Man 2", "PS5", "Ação", 21.00),
    new Jogo("Cyberpunk 2077", "PC", "RPG", 15.50),
    new Jogo("It Takes Two", "PS5", "Cooperativo", 11.90),
    new Jogo("Hades", "PC", "Roguelike", 9.90),
    new Jogo("Stardew Valley", "PC", "Simulação", 8.50),
    new Jogo("Ghost of Tsushima", "PS5", "Aventura", 19.50),
    new Jogo("Valorant", "PC", "FPS", 0.00),
    new Jogo("Sea of Thieves", "Xbox Series X", "Aventura", 14.90)
);

Banco.locacoes.push(
    new Locacao(Banco.jogos[0], Banco.clientes[1], "01/07/2026", "04/07/2026"),
    new Locacao(Banco.jogos[3], Banco.clientes[4], "02/07/2026", "06/07/2026"),
    new Locacao(Banco.jogos[7], Banco.clientes[8], "03/07/2026", "08/07/2026"),
    new Locacao(Banco.jogos[11], Banco.clientes[12], "04/07/2026", "07/07/2026"),
    new Locacao(Banco.jogos[15], Banco.clientes[17], "05/07/2026", "10/07/2026")
);

// Marcar os jogos como indisponíveis
Banco.jogos[0].setDisponivel(false);
Banco.jogos[3].setDisponivel(false);
Banco.jogos[7].setDisponivel(false);
Banco.jogos[11].setDisponivel(false);
Banco.jogos[15].setDisponivel(false);
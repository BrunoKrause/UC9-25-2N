import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    private static final String URL = "jdbc:postgresql://localhost/EscolaGrafica";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "root";
    
    public static Connection conectar() {
        
        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão Realizada com sucesso!");
            return conexao;
        } catch (SQLException erro) {
            System.out.println("Ocorreu um erro");
            System.out.println(erro.getMessage());
            return null;
        }
    }
}

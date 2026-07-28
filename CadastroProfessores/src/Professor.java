import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Professor {
    private int id;
    private String nome;
    private String disciplina;
    private String email;
    private String telefone;
    
    public Professor() {
        
    }
    
    public Professor(int id, String nome, String disciplina, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.disciplina = disciplina;
        this.email = email;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public boolean excluir(int Id) {
        String sql = "DELETE FROM professores WHERE id = ?";
        
        try {
            Connection conexao = Conexao.conectar();
            
            if(conexao == null) {
                System.out.println("Não foi possível conectar");
                return false;
            }
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, Id);
            
            int linhas = stmt.executeUpdate();
            
            stmt.close();
            conexao.close();
            return linhas > 0;
                
        } catch (SQLException erro){
            System.out.println("Ocorreu um erro na exclusão");
            System.out.println(erro.getMessage());
            return false;
        }
    }
    
    public boolean cadastrar(String nome, String disciplina, String email, String telefone) {
        
        String sql = """
              INSERT INTO professores
              (nome, disciplina, email, telefone)
              values (?, ?, ?, ?)
              """;
        
        try {
            Connection conexao = Conexao.conectar();
            if (conexao == null){
                System.out.println("Não foi possível conectar");
                return false;
            }
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2,disciplina);
            stmt.setString(3, email);
            stmt.setString(4, telefone);
            
            int linhas = stmt.executeUpdate();
            
            stmt.close();
            conexao.close();
            return linhas > 0;
        } catch (SQLException erro){
            System.out.println("Ocorreu um erro no cadastro");
            System.out.println(erro.getMessage());
            return false;
        }
    }
    
    public ArrayList<Professor> listar() {
        
        ArrayList<Professor> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM professores ORDER BY id";
        
        try {
            Connection conexao = Conexao.conectar();
            
            if (conexao == null){
                System.out.println("Não foi possível conectar");
                return lista;
            }
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                Professor professor = new Professor();
                professor.setId(resultado.getInt("id"));
                professor.setNome(resultado.getString("nome"));
                professor.setDisciplina(resultado.getString("disciplina"));
                professor.setEmail(resultado.getString("email"));
                professor.setTelefone(resultado.getString("telefone"));
                
                lista.add(professor);
            }
            
            resultado.close();
            conexao.close();
            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Ocorreu um erro ao carregar os professores.");
            System.out.println(erro.getMessage());
            return lista;
        }
        
        return lista;
    }
}

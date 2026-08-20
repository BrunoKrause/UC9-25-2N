package dao;

import conexao.Conexao;
import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    
    public long cadastrar (Cliente cliente) {
        String sql = """
                     INSERT INTO cliente (nome, cpf, telefone, email, endereco, numero, complemento, bairro, cidade, uf, cep, ativo)
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                     RETURNING id_cliente
                     """;
        
        try {
            Connection conexao = Conexao.conectar();
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, textoOuNull(cliente.getCpf()));
            stmt.setString(3, textoOuNull(cliente.getTelefone()));
            stmt.setString(4, textoOuNull(cliente.getEmail()));
            stmt.setString(5, textoOuNull(cliente.getEndereco()));
            stmt.setString(6, textoOuNull(cliente.getNumero()));
            stmt.setString(7, textoOuNull(cliente.getComplemento()));
            stmt.setString(8, textoOuNull(cliente.getBairro()));
            stmt.setString(9, textoOuNull(cliente.getCidade()));
            stmt.setString(10, textoOuNull(cliente.getUf()));
            stmt.setString(11, textoOuNull(cliente.getCep()));
            stmt.setBoolean(12, cliente.isAtivo());
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()){
                return rs.getLong("id_cliente");
            }
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao cadastrar cliente", erro);
        }
        return 0;
    }
    
    public boolean alterar(Cliente cliente) {
        String sql = """
                     UPDATE cliente
                     SET nome = ?, cpf = ?, telefone = ?, email = ?, endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, ativo = ?
                     Where id_cliente = ?
                     """;
        
        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setLong(13, cliente.getIdCliente());
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, textoOuNull(cliente.getCpf()));
            stmt.setString(3, textoOuNull(cliente.getTelefone()));
            stmt.setString(4, textoOuNull(cliente.getEmail()));
            stmt.setString(5, textoOuNull(cliente.getEndereco()));
            stmt.setString(6, textoOuNull(cliente.getNumero()));
            stmt.setString(7, textoOuNull(cliente.getComplemento()));
            stmt.setString(8, textoOuNull(cliente.getBairro()));
            stmt.setString(9, textoOuNull(cliente.getCidade()));
            stmt.setString(10, textoOuNull(cliente.getUf()));
            stmt.setString(11, textoOuNull(cliente.getCep()));
            stmt.setBoolean(12, cliente.isAtivo());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            throw new RuntimeException("Err ao alterar cliente", erro);
        }
    }
    
    public boolean excluir(long idCliente) {
        String sql = """
                     DELETE FROM cliente
                     WHERE id_cliente = ?
                     """;
        
        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setLong(1, idCliente);
            return stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            throw new RuntimeException("Não foi possível excluir o cliente", erro);
        }
    }
    
    private String textoOuNull(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return null;
        }
        return texto.trim();
    }
}

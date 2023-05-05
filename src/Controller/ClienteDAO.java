package Controller;

import Model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO {
    private PreparedStatement pst;
    private ResultSet rs;
    private String consultaCliente = "SELECT * FROM cliente";
    private String incluirCliente = "INSERT INTO cliente (nome, endereco, bairro, cidade, uf, cep, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private String alterarCliente = "UPDATE cliente SET nome = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ? WHERE cliente.id = ?;";
    private String excluirCliente = "DELETE FROM cliente WHERE cliente.id = ?;";
    private String pesquisaCliente = "SELECT * FROM cliente WHERE nome LIKE ?;";
    
    public List<Cliente> consultaCliente(){
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        Cliente cliente;
        try{
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(consultaCliente);
            rs = pst.executeQuery();
            while(rs.next()){
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("uf"));
                cliente.setCep(rs.getString("cep"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));                        
                listaClientes.add(cliente);
            }
            Conexao.desconectar(Conexao.conectar());
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        return listaClientes;
    }
    
    public boolean incluirCliente(Cliente cliente){
        try{
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(incluirCliente);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getEndereco());
            pst.setString(3, cliente.getBairro());
            pst.setString(4, cliente.getCidade());
            pst.setString(5, cliente.getUf());
            pst.setString(6, cliente.getCep());
            pst.setString(7, cliente.getTelefone());
            pst.setString(8, cliente.getEmail());
            pst.executeUpdate();
            Conexao.desconectar(Conexao.conectar());
            return true;
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        
        return false;
    }
  
    public boolean alterarCliente(Cliente cliente){
        try{
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(alterarCliente);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getEndereco());
            pst.setString(3, cliente.getBairro());
            pst.setString(4, cliente.getCidade());
            pst.setString(5, cliente.getUf());
            pst.setString(6, cliente.getCep());
            pst.setString(7, cliente.getTelefone());
            pst.setString(8, cliente.getEmail());
            pst.setInt(9, cliente.getId());
            pst.executeUpdate();
            Conexao.desconectar(Conexao.conectar());
            return true;
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        return false;
    }
    
    public boolean excluirCliente(Cliente cliente){
        try{
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(excluirCliente);
            pst.setInt(1, cliente.getId());
            pst.executeUpdate();
            Conexao.desconectar(Conexao.conectar());
            return true;
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        return false;
    }
    
    
    public List<Cliente> pesquisaCliente(String nome){
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        Cliente cliente;
        try{
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(pesquisaCliente);
            pst.setString(1, nome);
            rs = pst.executeQuery();
            while(rs.next()){
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("uf"));
                cliente.setCep(rs.getString("cep"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));                 
                listaClientes.add(cliente);
            }
            Conexao.desconectar(Conexao.conectar());
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        return listaClientes;
    }
    
}

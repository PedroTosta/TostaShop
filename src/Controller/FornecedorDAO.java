package Controller;

import Model.Fornecedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {
    private PreparedStatement pst;
    private ResultSet rs;
    private String consultaFornecedor = "SELECT * FROM fornecedor;";
    private String incluirFornecedor = "INSERT INTO fornecedor (nome, endereco, bairro, cidade, uf, cep, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private String alterarFornecedor = "UPDATE fornecedor SET nome = ?, endereco = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, telefone = ?, email = ? WHERE fornecedor.id = ?;";
    private String excluirFornecedor = "DELETE FROM fornecedor WHERE fornecedor.id = ?;";
    private String pesquisaFornecedor = "SELECT * FROM fornecedor WHERE nome LIKE ?;";
    
    public List<Fornecedor> consultaFornecedor(){
        List<Fornecedor> listaFornecedores = new ArrayList<Fornecedor>();
        Fornecedor fornecedor;
        try{
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(consultaFornecedor);
            rs = pst.executeQuery();
            while(rs.next()){
                fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setUf(rs.getString("uf"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));                        
                listaFornecedores.add(fornecedor);
            }
            Conexao.desconectar(Conexao.conectar());
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        return listaFornecedores;
    }
    
    public boolean incluirFornecedor(Fornecedor fornecedor){
        try{
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(incluirFornecedor);
            pst.setString(1, fornecedor.getNome());
            pst.setString(2, fornecedor.getEndereco());
            pst.setString(3, fornecedor.getBairro());
            pst.setString(4, fornecedor.getCidade());
            pst.setString(5, fornecedor.getUf());
            pst.setString(6, fornecedor.getCep());
            pst.setString(7, fornecedor.getTelefone());
            pst.setString(8, fornecedor.getEmail());
            pst.executeUpdate();
            Conexao.desconectar(Conexao.conectar());
            return true;
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        
        return false;
    }
  
    public boolean alterarFornecedor(Fornecedor fornecedor){
        try{
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(alterarFornecedor);
            pst.setString(1, fornecedor.getNome());
            pst.setString(2, fornecedor.getEndereco());
            pst.setString(3, fornecedor.getBairro());
            pst.setString(4, fornecedor.getCidade());
            pst.setString(5, fornecedor.getUf());
            pst.setString(6, fornecedor.getCep());
            pst.setString(7, fornecedor.getTelefone());
            pst.setString(8, fornecedor.getEmail());
            pst.setInt(9, fornecedor.getId());
            pst.executeUpdate();
            Conexao.desconectar(Conexao.conectar());
            return true;
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        return false;
    }
    
    public boolean excluirFornecedor(Fornecedor fornecedor){
        try{
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(excluirFornecedor);
            pst.setInt(1, fornecedor.getId());
            pst.executeUpdate();
            Conexao.desconectar(Conexao.conectar());
            return true;
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        return false;
    }
    
    
    public List<Fornecedor> pesquisaFornecedor(String nome){
        List<Fornecedor> listaFornecedores = new ArrayList<Fornecedor>();
        Fornecedor fornecedor;
        try{
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(pesquisaFornecedor);
            pst.setString(1, nome);
            rs = pst.executeQuery();
            while(rs.next()){
                fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setUf(rs.getString("uf"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));                 
                listaFornecedores.add(fornecedor);
            }
            Conexao.desconectar(Conexao.conectar());
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        return listaFornecedores;
    }    
    
}

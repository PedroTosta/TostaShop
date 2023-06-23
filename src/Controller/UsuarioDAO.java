package Controller;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO {  
    private PreparedStatement pst;
    private ResultSet rs;
    private String consultaUsuario = "SELECT * FROM usuario";
    private String incluirUsuario = "INSERT INTO usuario (nome, usuario, email, senha, perfil) VALUES (?, ?, ?, md5(?), ?);";
    private String alterarUsuario = "UPDATE usuario SET nome = ?, usuario = ?, email = ?, perfil = ? WHERE usuario.id = ?;";
    private String excluirUsuario = "DELETE FROM usuario WHERE usuario.id = ?;";
    private String pesquisaUsuario = "SELECT * FROM usuario WHERE nome LIKE ?;";
    
    public List<Usuario> consultaUsuario(){
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        Usuario usuario;
        try{
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(consultaUsuario);
            rs = pst.executeQuery();
            while(rs.next()){
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setPerfil(rs.getString("perfil"));
                usuario.setEmail(rs.getString("email"));
                listaUsuarios.add(usuario);
            }
            Conexao.desconectar(Conexao.conectar());
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        return listaUsuarios;
    }
    
    
    public boolean incluirUsuario(Usuario usuario){
        try{
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(incluirUsuario);
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getUsuario());
            pst.setString(4, usuario.getSenha());
            pst.setString(5, usuario.getPerfil());
            pst.executeUpdate();
            Conexao.desconectar(Conexao.conectar());
            return true;
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        
        return false;
    }

    
    public boolean alterarUsuario(Usuario usuario){
        try{
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(alterarUsuario);
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getEmail());
            pst.setString(3, usuario.getUsuario());
            pst.setString(4, usuario.getPerfil());
            pst.setInt(5, usuario.getId());
            pst.executeUpdate();
            Conexao.desconectar(Conexao.conectar());
            return true;
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        return false;
    }
    
    public boolean excluirUsuario(Usuario usuario){
        try{
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(excluirUsuario);
            pst.setInt(1, usuario.getId());
            pst.executeUpdate();
            Conexao.desconectar(Conexao.conectar());
            return true;
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        return false;
    }
    
    
    public List<Usuario> pesquisaUsuario(String nome){
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        Usuario usuario;
        try{
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(pesquisaUsuario);
            pst.setString(1, nome);
            rs = pst.executeQuery();
            while(rs.next()){
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setPerfil(rs.getString("perfil"));
                usuario.setEmail(rs.getString("email"));                
                listaUsuarios.add(usuario);
            }
            Conexao.desconectar(Conexao.conectar());
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        return listaUsuarios;
    }
    
}

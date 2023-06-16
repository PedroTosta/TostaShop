package Controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    
    public static Connection conectar(){
        Connection conexao = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost/tostashop";
        String usuario = "root";
        String senha = "root";
        
        try{
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            return conexao;
        }catch(Exception e){
            System.out.println("ERROR: "+e.getMessage());
            return null;
        }
    }
    
    public static void desconectar(Connection conexao){
        try{
            if(conexao != null && !conexao.isClosed()){
                conexao.close();
            }
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
    }
}
package Controller;;

import Model.Fornecedor;
import Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private PreparedStatement pst;
    private Connection con;
    private ResultSet rs;
    //private AcessoBD acessoBD = new AcessoBD();
    
    //private String consultaProduto = "SELECT p.id, p.id_fornecedor, p.nome, p.qtde_estoque, FORMAT(valor, 2, 'pt_BR'), f.* FROM produto p JOIN fornecedor f on p.id_fornecedor = f.id";
    private String consultaProduto = "SELECT p.*, f.* FROM produto p JOIN fornecedor f on p.id_fornecedor = f.id";
    private String consultaProdutoNome = "SELECT p.*, f.* FROM produto p JOIN fornecedor f on p.id_fornecedor = f.id WHERE p.nome like ?";
    private String incluiProduto = "INSERT INTO produto (nome, id_fornecedor, qtde_estoque, valor) VALUES(?, ?, ?, ?)";
    private String alteraProduto = "UPDATE produto SET nome = ?, id_fornecedor = ?, qtde_estoque = ?, valor = ? WHERE produto.id = ?";
    private String excluiProduto = "DELETE FROM produto WHERE produto.id = ?";

    public List<Produto> consultaProduto() {
        List<Produto> listaProdutos = new ArrayList<Produto>();
        Produto produto;
        try {
            con = Conexao.conectar();
            pst = con.prepareStatement(consultaProduto);
            rs = pst.executeQuery();
            while (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("p.id"));
                produto.setNome(rs.getString("p.nome"));
                produto.setQtdeEstoque(rs.getInt("p.qtde_estoque"));
                //produto.setValor(rs.getDouble("FORMAT(valor, 2, 'pt_BR')"));
                
                /*String valor = String.valueOf(rs.getDouble("p.valor"));
                valor = valor.replace(".", ",");
                produto.setValor(Double.parseDouble(valor));*/
                produto.setValor(rs.getDouble("p.valor"));

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("f.id"));
                fornecedor.setNome(rs.getString("f.nome"));
                fornecedor.setEndereco(rs.getString("f.endereco"));
                fornecedor.setBairro(rs.getString("f.bairro"));
                fornecedor.setCidade(rs.getString("f.cidade"));
                fornecedor.setCep(rs.getString("f.cep"));
                fornecedor.setUf(rs.getString("f.uf"));
                fornecedor.setTelefone(rs.getString("f.telefone"));
                fornecedor.setEmail(rs.getString("f.email"));

                produto.setFornecedor(fornecedor);

                listaProdutos.add(produto);
            }
            Conexao.desconectar(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProdutos;
    }

    public List<Produto> consultaProduto(String nome) {
        List<Produto> listaProdutos = new ArrayList<Produto>();
        Produto produto;
        try {
            con = Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(consultaProdutoNome);
            nome = "%" + nome + "%";
            pst.setString(1, nome);
            rs = pst.executeQuery();
            while (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("p.id"));
                produto.setNome(rs.getString("p.nome"));
                produto.setQtdeEstoque(rs.getInt("p.qtde_estoque"));
                produto.setValor(rs.getDouble("p.valor"));

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("f.id"));
                fornecedor.setNome(rs.getString("f.nome"));
                fornecedor.setEndereco(rs.getString("f.endereco"));
                fornecedor.setBairro(rs.getString("f.bairro"));
                fornecedor.setCidade(rs.getString("f.cidade"));
                fornecedor.setCep(rs.getString("f.cep"));
                fornecedor.setUf(rs.getString("f.uf"));
                fornecedor.setTelefone(rs.getString("f.telefone"));
                fornecedor.setEmail(rs.getString("f.email"));

                produto.setFornecedor(fornecedor);

                listaProdutos.add(produto);
            }
            Conexao.desconectar(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProdutos;
    }

    public boolean incluiProduto(Produto produto) {
        try {
            con = Conexao.conectar();
            pst = con.prepareStatement(incluiProduto);

            pst.setString(1, produto.getNome());
            pst.setInt(2, produto.getFornecedor().getId());
            pst.setInt(3, produto.getQtdeEstoque());
            pst.setDouble(4, produto.getValor());

            pst.executeUpdate();

            Conexao.desconectar(con);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean alteraProduto(Produto produto) {
        try {
            con = Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(alteraProduto);

            pst.setString(1, produto.getNome());
            pst.setInt(2, produto.getFornecedor().getId());
            pst.setInt(3, produto.getQtdeEstoque());
            pst.setDouble(4, produto.getValor());
            pst.setInt(5, produto.getId());

            pst.executeUpdate();

            Conexao.desconectar(con);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean excluiProduto(Produto produto) {
        try {
            con = Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(excluiProduto);

            pst.setInt(1, produto.getId());

            pst.executeUpdate();

            Conexao.desconectar(con);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}

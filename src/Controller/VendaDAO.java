package Controller;

import Model.Cliente;
import Model.ItensVenda;
import Model.Produto;
import Model.Venda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cleber Feitosa
 */
public class VendaDAO {

    private PreparedStatement pst;
    private ResultSet rs;
    private ResultSet rs2;
    private Connection con;
    private String registraVenda = "INSERT INTO venda (id_cliente, data_venda) values(?, ?)";
    private String registraItensVenda = "insert into itens_venda (id_produto, id_venda, qtde, valor) values(?, ?, ?, ?)";
    private String consultaUltimoId = "select MAX(id) as id from venda";
    private String consultaVendaPeriodo = "select v.*, c.* from venda v join cliente c on v.id_cliente = c.id where v.data_venda between ? and ?";
    private String consultaItensVenda = "select i.*, p.* from itens_venda i join produto p on i.id_produto = p.id where i.id_venda = ?";

    public boolean registrarVenda(Venda venda) {
        try {
            con = Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(registraVenda);
            pst.setInt(1, venda.getCliente().getId());
            pst.setDate(2, venda.getDataVenda());

            pst.executeUpdate();

            pst = Conexao.conectar().prepareStatement(consultaUltimoId);
            rs = pst.executeQuery();
            rs.next();
            venda.setId(rs.getInt("id"));

            for (int i = 0; i < venda.getItensVenda().size(); i++) {
                pst = Conexao.conectar().prepareStatement(registraItensVenda);
                pst.setInt(1, venda.getItensVenda().get(i).getProduto().getId());
                pst.setInt(2, venda.getId());
                pst.setInt(3, venda.getItensVenda().get(i).getQtde());
                pst.setDouble(4, venda.getItensVenda().get(i).getValor());

                pst.executeUpdate();
            }

            Conexao.desconectar(con);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Venda> consultaVendaPeriodo(Date dataInicial, Date dataFinal) {
        List<Venda> listaVendas = new ArrayList<Venda>();
        Venda venda;
        try {
            Conexao.conectar();
            pst = Conexao.conectar().prepareStatement(consultaVendaPeriodo);
            pst.setDate(1, dataInicial);
            pst.setDate(2, dataFinal);

            rs = pst.executeQuery();
            while (rs.next()) {
                venda = new Venda();
                venda.setId(rs.getInt("v.id"));
                venda.setDataVenda(rs.getDate("v.data_venda"));

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("c.id"));
                cliente.setNome(rs.getString("c.nome"));
                cliente.setEndereco(rs.getString("c.endereco"));
                cliente.setBairro(rs.getString("c.bairro"));
                cliente.setCidade(rs.getString("c.cidade"));
                cliente.setCep(rs.getString("c.cep"));
                cliente.setUf(rs.getString("c.uf"));
                cliente.setTelefone(rs.getString("c.telefone"));
                cliente.setEmail(rs.getString("c.email"));

                venda.setCliente(cliente);

                pst = Conexao.conectar().prepareStatement(consultaItensVenda);
                pst.setInt(1, venda.getId());
                rs2 = pst.executeQuery();

                List<ItensVenda> listaItensVenda = new ArrayList<ItensVenda>();
                while(rs2.next()){
                    ItensVenda itenVenda = new ItensVenda();
                    itenVenda.setId(rs2.getInt("i.id"));
                    itenVenda.setQtde(rs2.getInt("i.qtde"));
                    itenVenda.setValor(rs2.getDouble("i.valor"));

                    Produto produto = new Produto();
                    produto.setId(rs2.getInt("p.id"));
                    produto.setNome(rs2.getString("p.nome"));
                    produto.setQtdeEstoque(rs2.getInt("p.qtde_estoque"));
                    produto.setValor(rs2.getDouble("p.valor"));

                    itenVenda.setProduto(produto);

                    listaItensVenda.add(itenVenda);
                }
                venda.setItensVenda(listaItensVenda);
                listaVendas.add(venda);
            }
            Conexao.desconectar(con);
            return listaVendas;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaVendas;
    }
}

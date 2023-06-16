package View;

import Controller.ProdutoDAO;
import Controller.VendaDAO;
import Model.Cliente;
import Model.ItensVenda;
import Model.Produto;
import Model.Venda;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class RegistrarVendaFrame extends javax.swing.JInternalFrame {

    private Produto produto;
    private DefaultTableModel tableModel;
    private Cliente cliente;
    private List<ItensVenda> itensVenda;

    public RegistrarVendaFrame() {
        initComponents();
        defineModelo();
        itensVenda = new ArrayList<ItensVenda>();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        jTextProduto.setText(produto.getNome());
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        jTextCliente.setText(cliente.getNome());
    }

    public void defineModelo() {
        tableModel = (DefaultTableModel) jTableProdutos.getModel();
        try {
            DecimalFormat formatoValor = new DecimalFormat("#,###.00");
            NumberFormatter formatterValor = new NumberFormatter(formatoValor);
            formatterValor.setValueClass(Double.class);
            jTextValorTotal.setFormatterFactory(new DefaultFormatterFactory(formatterValor));

            DecimalFormat formatoEstoque = new DecimalFormat("#,###");
            NumberFormatter formatterEstoque = new NumberFormatter(formatoEstoque);
            formatterEstoque.setValueClass(Integer.class);
            jTextQtde.setFormatterFactory(new DefaultFormatterFactory(formatterEstoque));

            jTableProdutos.getColumnModel().getColumn(0).setPreferredWidth(400);
        } catch (Exception ex) {
            Logger.getLogger(RegistrarVendaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*public void listar() {
        double valorTotal = 0.0;
        for (int i = 0; i < itensVenda.size(); i++) {
            tableModel.insertRow(i, new Object[]{itensVenda.get(i).getProduto().getNome(),
                itensVenda.get(i).getQtde(),
                itensVenda.get(i).getProduto().getValor(),
                itensVenda.get(i).getProduto().getValor() * itensVenda.get(i).getQtde()
            });
            valorTotal += itensVenda.get(i).getProduto().getValor() * itensVenda.get(i).getQtde();
        }
        jTextValorTotal.setValue(valorTotal);        
    }  */
    public void listar() {
        int index = jTableProdutos.getRowCount();
        double valorTotal = 0.0;
        tableModel.addRow(new Object[]{itensVenda.get(index).getProduto().getNome(),
            itensVenda.get(index).getQtde(),
            itensVenda.get(index).getProduto().getValor(),
            itensVenda.get(index).getProduto().getValor() * itensVenda.get(index).getQtde()
        });

        for(int i = 0; i < itensVenda.size(); i++){
            valorTotal += itensVenda.get(i).getProduto().getValor() * itensVenda.get(i).getQtde();
        }
        jTextValorTotal.setValue(valorTotal);
    }

    public void incluirProduto() {
        if (produto == null) {
            JOptionPane.showMessageDialog(this, "Selecionar o produto!", "Erro", JOptionPane.ERROR_MESSAGE);
            btnSelecionaProduto.requestFocus();
        } else if (jTextQtde.getText() == null) {
            JOptionPane.showMessageDialog(this, "Informe a quantidade!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            ItensVenda item = new ItensVenda();
            item.setProduto(produto);
            item.setQtde((Integer) jTextQtde.getValue());
            item.setValor(produto.getValor());

            itensVenda.add(item);
            listar();
        }

        limparCampos();
    }

    private void excluirProduto() {
        if (jTableProdutos.getSelectedRow() != -1) {
            itensVenda.remove(jTableProdutos.getSelectedRow());
            listar();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item da lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limparCampos() {
        jTextCliente.setText("");
        jTextProduto.setText("");
        jTextQtde.setText("");
        produto = null;
        cliente = null;
    }

    private void registrarVenda() {
        if (getCliente() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (itensVenda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Insira itens na venda!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            Venda venda = new Venda();
            venda.setCliente(getCliente());

            Calendar dataAtual = Calendar.getInstance();
            venda.setDataVenda(new java.sql.Date(dataAtual.getTime().getTime()));

            venda.setItensVenda(itensVenda);

            VendaDAO vendaBD = new VendaDAO();
            if (vendaBD.registrarVenda(venda)) {
                JOptionPane.showMessageDialog(this, "Venda registrada com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextCliente = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextProduto = new javax.swing.JTextField();
        btnSelecionaProduto = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProdutos = new javax.swing.JTable();
        jTextQtde = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextValorTotal = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Registrar Venda");
        setPreferredSize(new java.awt.Dimension(700, 550));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registrar Venda");
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Cliente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jTextCliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextCliente.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jTextCliente, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jButton1, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        jTextProduto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextProduto.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jTextProduto, gridBagConstraints);

        btnSelecionaProduto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnSelecionaProduto.setText("...");
        btnSelecionaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionaProdutoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(btnSelecionaProduto, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Qtde");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel4, gridBagConstraints);

        jButton3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton3.setText("Incluir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jButton3, gridBagConstraints);

        jTableProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Qtde", "Valor unitário", "Valor total"
            }
        ));
        jScrollPane1.setViewportView(jTableProdutos);
        if (jTableProdutos.getColumnModel().getColumnCount() > 0) {
            jTableProdutos.getColumnModel().getColumn(0).setMinWidth(1);
            jTableProdutos.getColumnModel().getColumn(0).setMaxWidth(550);
            jTableProdutos.getColumnModel().getColumn(1).setMinWidth(1);
            jTableProdutos.getColumnModel().getColumn(1).setMaxWidth(100);
            jTableProdutos.getColumnModel().getColumn(2).setMinWidth(1);
            jTableProdutos.getColumnModel().getColumn(2).setMaxWidth(250);
            jTableProdutos.getColumnModel().getColumn(3).setMinWidth(1);
            jTableProdutos.getColumnModel().getColumn(3).setMaxWidth(250);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jScrollPane1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jTextQtde, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Valor total");
        jPanel3.add(jLabel5);

        jTextValorTotal.setEnabled(false);
        jTextValorTotal.setPreferredSize(new java.awt.Dimension(100, 22));
        jPanel3.add(jTextValorTotal);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButton4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton4.setText("Excluir Item");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4);

        jButton5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton5.setText("Salvar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelecionaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionaProdutoActionPerformed
        ProdutoFrame pf = new ProdutoFrame(this);
        pf.setVisible(true);
        this.getDesktopPane().add(pf);
        pf.toFront();
    }//GEN-LAST:event_btnSelecionaProdutoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ClienteFrame cf = new ClienteFrame(this);
        cf.setVisible(true);
        this.getDesktopPane().add(cf);
        cf.toFront();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        incluirProduto();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        excluirProduto();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        registrarVenda();
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelecionaProduto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProdutos;
    private javax.swing.JTextField jTextCliente;
    private javax.swing.JTextField jTextProduto;
    private javax.swing.JFormattedTextField jTextQtde;
    private javax.swing.JFormattedTextField jTextValorTotal;
    // End of variables declaration//GEN-END:variables
}

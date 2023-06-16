package View;

import Controller.ProdutoDAO;
import Model.Fornecedor;
import Model.Produto;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class ProdutoFrame extends javax.swing.JInternalFrame {
    
    
    
    private int modo;
    List<Produto> lista;
    private Fornecedor fornecedor;
    private RegistrarVendaFrame registraVenda;
    
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
        jTextFornecedor.setText(fornecedor.getNome());
    }
    
    public ProdutoFrame() {
        initComponents();        
        listar();
        btnSelecionaProduto.setVisible(false);
    }
    
    public ProdutoFrame(RegistrarVendaFrame registraVenda) {
        initComponents();
        try {
            DecimalFormat formatoValor = new DecimalFormat("#,###.00");
            NumberFormatter formatterValor = new NumberFormatter(formatoValor);
            formatterValor.setValueClass(Double.class);
            jTextValor.setFormatterFactory(new DefaultFormatterFactory(formatterValor));

            DecimalFormat formatoEstoque = new DecimalFormat("#,###");
            NumberFormatter formatterEstoque = new NumberFormatter(formatoEstoque);
            formatterEstoque.setValueClass(Integer.class);
            jTextEstoque.setFormatterFactory(new DefaultFormatterFactory(formatterEstoque));
        } catch (Exception ex) {
            Logger.getLogger(ProdutoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        listar();
        btnSelecionaProduto.setVisible(true);
        this.registraVenda = registraVenda;
    }
                   
    public void listar(){
        ProdutoDAO produtoDao = new ProdutoDAO();
        lista = produtoDao.consultaProduto();
        DefaultTableModel dados = (DefaultTableModel) jTableProdutos.getModel();
        dados.setNumRows(0);
        for (Produto produto : lista) {
            dados.addRow(new Object[]{
                produto.getId(),
                produto.getNome()
            });
        }
        
        try {
            DecimalFormat formatoValor = new DecimalFormat("#,###.00");
            NumberFormatter formatterValor = new NumberFormatter(formatoValor);
            formatterValor.setValueClass(Double.class);
            jTextValor.setFormatterFactory(new DefaultFormatterFactory(formatterValor));

            DecimalFormat formatoEstoque = new DecimalFormat("#,###");
            NumberFormatter formatterEstoque = new NumberFormatter(formatoEstoque);
            formatterEstoque.setValueClass(Integer.class);
            jTextEstoque.setFormatterFactory(new DefaultFormatterFactory(formatterEstoque));
        } catch (Exception ex) {
            Logger.getLogger(ProdutoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrar() {
        int index = jTableProdutos.getSelectedRow();
        if (jTableProdutos.getSelectedRow() != -1) {                        
            jTextNome.setText(lista.get(index).getNome());
            jTextEstoque.setText(String.valueOf(lista.get(index).getQtdeEstoque()));
            jTextValor.setText(String.valueOf(lista.get(index).getValor()));
            setFornecedor(lista.get(index).getFornecedor());            
        }
    }
    
    private void habilitarCampos() {
        jTextNome.setEnabled(true);
        jTextEstoque.setEnabled(true);
        jTextValor.setEnabled(true);
        jTextFornecedor.setEnabled(true);
        btnSelecionaFornecedor.setEnabled(true);
    }

    private void desabilitarCampos() {
        jTextNome.setEnabled(false);
        jTextEstoque.setEnabled(false);
        jTextValor.setEnabled(false);
        jTextFornecedor.setEnabled(false);
        btnSelecionaFornecedor.setEnabled(false);
    }

    private void habilitarBotoes() {
        jButtonAlterar.setEnabled(false);
        jButtonExcluir.setEnabled(false);
        jButtonNovo.setEnabled(false);
        jButtonSalvar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
    }

    private void desabilitarBotoes() {
        jButtonAlterar.setEnabled(true);
        jButtonExcluir.setEnabled(true);
        jButtonNovo.setEnabled(true);
        jButtonSalvar.setEnabled(false);
        jButtonCancelar.setEnabled(false);
    }

    private void limparCampos() {
        jTextNome.setText("");
        jTextEstoque.setText("");
        jTextValor.setText("");
        jTextFornecedor.setText("");
    }
    
    public void excluirProduto() {
        Produto produto = new Produto();
        produto.setId((Integer) jTableProdutos.getValueAt(jTableProdutos.getSelectedRow(), 0));

        ProdutoDAO produtoDao = new ProdutoDAO();
        if (produtoDao.excluiProduto(produto)) {
            JOptionPane.showMessageDialog(this, "Produto excluido com sucesso!!!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            listar();
            limparCampos();
            desabilitarBotoes();           
            desabilitarCampos();            
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir produto", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void incluiProduto() {
        if (jTextNome.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o nome do produto", "ERRO", JOptionPane.ERROR_MESSAGE);
            jTextNome.requestFocus();
        } else if(getFornecedor() == null){
            JOptionPane.showMessageDialog(this, "Informe um fornecedor", "ERRO", JOptionPane.ERROR_MESSAGE);
        }else{
            Produto produto = new Produto();
            produto.setNome(jTextNome.getText().trim());
            produto.setQtdeEstoque((Integer) jTextEstoque.getValue());
            produto.setValor((Double) jTextValor.getValue());
            produto.setFornecedor(getFornecedor());
            
            ProdutoDAO produtoDao = new ProdutoDAO();
            if (produtoDao.incluiProduto(produto)) {
                JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!!!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                listar();
                desabilitarBotoes();
                limparCampos();
                desabilitarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar produto", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void alteraProduto() {
        if (jTextNome.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o nome do produto", "ERRO", JOptionPane.ERROR_MESSAGE);
            jTextNome.requestFocus();
        } else if(getFornecedor() == null){
            JOptionPane.showMessageDialog(this, "Informe um fornecedor", "ERRO", JOptionPane.ERROR_MESSAGE);
        }else{
            Produto produto = new Produto();
            produto.setNome(jTextNome.getText().trim());
            produto.setQtdeEstoque((Integer) jTextEstoque.getValue());
            produto.setValor((Double) jTextValor.getValue());
            produto.setFornecedor(getFornecedor());

            ProdutoDAO produtoDao = new ProdutoDAO();
            if (produtoDao.alteraProduto(produto)) {
                JOptionPane.showMessageDialog(this, "Produto alterado com sucesso!!!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                listar();
                desabilitarBotoes();
                desabilitarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao alterar produto", "ERRO", JOptionPane.ERROR_MESSAGE);
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
        jTextFiltroNome = new javax.swing.JTextField();
        jButtonPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProdutos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextNome = new javax.swing.JTextField();
        jTextValor = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFornecedor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnSelecionaFornecedor = new javax.swing.JButton();
        jTextEstoque = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        btnSelecionaProduto = new javax.swing.JButton();
        jButtonNovo = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Produto");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(700, 550));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Produtos");
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Filtro por nome ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jTextFiltroNome.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jTextFiltroNome, gridBagConstraints);

        jButtonPesquisar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/search.png"))); // NOI18N
        jButtonPesquisar.setToolTipText("Botão pesquisar cliente pelo nome");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jButtonPesquisar, gridBagConstraints);

        jTableProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableProdutos);
        if (jTableProdutos.getColumnModel().getColumnCount() > 0) {
            jTableProdutos.getColumnModel().getColumn(0).setMinWidth(1);
            jTableProdutos.getColumnModel().getColumn(0).setMaxWidth(200);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel3, gridBagConstraints);

        jTextNome.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextNome.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextNome, gridBagConstraints);

        jTextValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jTextValor.setEnabled(false);
        jTextValor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 85;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextValor, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Fornecedor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel4, gridBagConstraints);

        jTextFornecedor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextFornecedor.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextFornecedor, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Estoque");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel5, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Valor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel6, gridBagConstraints);

        btnSelecionaFornecedor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnSelecionaFornecedor.setText("...");
        btnSelecionaFornecedor.setEnabled(false);
        btnSelecionaFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionaFornecedorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(btnSelecionaFornecedor, gridBagConstraints);

        jTextEstoque.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        jTextEstoque.setEnabled(false);
        jTextEstoque.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 85;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextEstoque, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnSelecionaProduto.setText("Seleciona Produto");
        btnSelecionaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionaProdutoActionPerformed(evt);
            }
        });
        jPanel4.add(btnSelecionaProduto);

        jButtonNovo.setText("Novo");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonNovo);

        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonAlterar);

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonExcluir);

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.setEnabled(false);
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonSalvar);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setEnabled(false);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonCancelar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        String nome = "%" + jTextFiltroNome.getText() + "%";
        ProdutoDAO produtoDao = new ProdutoDAO();
        lista = produtoDao.consultaProduto(nome);
        DefaultTableModel dados = (DefaultTableModel) jTableProdutos.getModel();
        dados.setRowCount(0);
        for (Produto produto : lista) {
            dados.addRow(new Object[]{
                produto.getId(),
                produto.getNome(),});
        }
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        listar();
    }//GEN-LAST:event_formInternalFrameOpened

    private void jTableProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProdutosMouseClicked
        if (jTableProdutos.getSelectedRow() != -1) {
            mostrar();
        }
    }//GEN-LAST:event_jTableProdutosMouseClicked

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        habilitarCampos();
        habilitarBotoes();
        limparCampos();
        modo = Util.Constantes.INSERT_MODE;
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        if (jTableProdutos.getSelectedRow() != -1) {
            limparCampos();
            mostrar();
            habilitarCampos();
            habilitarBotoes();
            modo = Util.Constantes.EDIT_MODE;
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um produto na tabela", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        if (jTableProdutos.getSelectedRow() != -1) {
            int op = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja excluir o produto " + lista.get(jTableProdutos.getSelectedRow()).getNome(), "Confirmar exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (op == JOptionPane.YES_OPTION) {
                excluirProduto();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um produto na tabela", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        if (modo == Util.Constantes.INSERT_MODE) {
            incluiProduto();
        } else if (modo == Util.Constantes.EDIT_MODE) {
            alteraProduto();
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        desabilitarCampos();
        desabilitarBotoes();
        limparCampos();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void btnSelecionaFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionaFornecedorActionPerformed
        FornecedorFrame ff = new FornecedorFrame(this);
        ff.setVisible(true);
        this.getDesktopPane().add(ff);
        ff.toFront(); 
    }//GEN-LAST:event_btnSelecionaFornecedorActionPerformed

    private void btnSelecionaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionaProdutoActionPerformed
         if (jTableProdutos.getSelectedRow() != -1) {
            int numeroLinha = jTableProdutos.getSelectedRow();
            registraVenda.setProduto(lista.get(jTableProdutos.getSelectedRow()));
            this.dispose();
            registraVenda.toFront();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto da lista!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSelecionaProdutoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelecionaFornecedor;
    private javax.swing.JButton btnSelecionaProduto;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProdutos;
    private javax.swing.JFormattedTextField jTextEstoque;
    private javax.swing.JTextField jTextFiltroNome;
    private javax.swing.JTextField jTextFornecedor;
    private javax.swing.JTextField jTextNome;
    private javax.swing.JFormattedTextField jTextValor;
    // End of variables declaration//GEN-END:variables
}

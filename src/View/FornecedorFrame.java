package View;

import Controller.FornecedorDAO;
import Model.Fornecedor;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FornecedorFrame extends javax.swing.JInternalFrame {

    private int modo;
    List<Fornecedor> lista;
    private ProdutoFrame produtoFrame;
    
    public FornecedorFrame() {
        initComponents();
        listar();
        btnSelecionarFornecedor.setVisible(false);
    }
    
    public FornecedorFrame(ProdutoFrame produtoFrame) {
        initComponents();
        listar();
        btnSelecionarFornecedor.setVisible(true);
        this.produtoFrame = produtoFrame;
    }

    public void listar() {
        FornecedorDAO fornecedorDao = new FornecedorDAO();
        //List<Cliente> lista = clienteDao.consultaCliente();
        lista = fornecedorDao.consultaFornecedor();
        DefaultTableModel dados = (DefaultTableModel) jTableFornecedores.getModel();
        dados.setNumRows(0);
        for (Fornecedor fornecedor : lista) {
            dados.addRow(new Object[]{
                fornecedor.getId(),
                fornecedor.getNome()
            });
        }
    }
    
    private void habilitarCampos() {
        jTextBairro.setEnabled(true);
        jTextTelefone.setEnabled(true);
        jTextCidade.setEnabled(true);
        jTextEmail.setEnabled(true);
        jTextEndereco.setEnabled(true);
        jTextNome.setEnabled(true);
        jTextCep.setEnabled(true);
        cbUf.setEnabled(true);
    }

    private void desabilitarCampos() {
        jTextBairro.setEnabled(false);
        jTextTelefone.setEnabled(false);
        jTextCidade.setEnabled(false);
        jTextEmail.setEnabled(false);
        jTextEndereco.setEnabled(false);
        jTextNome.setEnabled(false);
        jTextCep.setEnabled(false);
        cbUf.setEnabled(false);
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
        jTextBairro.setText("");
        jTextTelefone.setText("");
        jTextCidade.setText("");
        jTextEmail.setText("");
        jTextEndereco.setText("");
        jTextNome.setText("");
        jTextCep.setText("");
        cbUf.setSelectedIndex(0);
    }
    
    public void incluiFornecedor() {
        if (jTextNome.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o nome do fornecedor", "ERRO", JOptionPane.ERROR_MESSAGE);
            jTextNome.requestFocus();
        } else {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNome(jTextNome.getText().trim());
            fornecedor.setEndereco(jTextEndereco.getText().trim());
            fornecedor.setBairro(jTextBairro.getText().trim());
            fornecedor.setCidade(jTextCidade.getText().trim());
            fornecedor.setUf(cbUf.getSelectedItem().toString());
            String cep = jTextCep.getText().replace("-", "");
            fornecedor.setCep(cep);
            String telefone = jTextTelefone.getText().replace("(", "");
            telefone = telefone.replace(")", "");
            telefone = telefone.replace("-", "");
            telefone = telefone.replace(" ", "");
            fornecedor.setTelefone(telefone);
            fornecedor.setEmail(jTextEmail.getText().trim());

            FornecedorDAO fornecedorDao = new FornecedorDAO();
            if (fornecedorDao.incluirFornecedor(fornecedor)) {
                JOptionPane.showMessageDialog(this, "Fornecedor cadastrado com sucesso!!!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                listar();
                desabilitarBotoes();
                limparCampos();
                desabilitarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar fornecedor", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void alteraFornecedor() {
        if (jTextNome.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Informe o nome do fornecedor", "ERRO", JOptionPane.ERROR_MESSAGE);
            jTextNome.requestFocus();
        } else {
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setId((Integer) jTableFornecedores.getValueAt(jTableFornecedores.getSelectedRow(), 0));
            fornecedor.setNome(jTextNome.getText().trim());
            fornecedor.setEndereco(jTextEndereco.getText().trim());
            fornecedor.setBairro(jTextBairro.getText().trim());
            fornecedor.setCidade(jTextCidade.getText().trim());
            fornecedor.setUf(cbUf.getSelectedItem().toString());
            String cep = jTextCep.getText().replace("-", "");
            fornecedor.setCep(cep);
            String telefone = jTextTelefone.getText().replace("(", "");
            telefone = telefone.replace(")", "");
            telefone = telefone.replace("-", "");
            fornecedor.setTelefone(telefone);
            fornecedor.setEmail(jTextEmail.getText().trim());

            FornecedorDAO fornecedorDao = new FornecedorDAO();
            if (fornecedorDao.alterarFornecedor(fornecedor)) {
                JOptionPane.showMessageDialog(this, "Fornecedor alterado com sucesso!!!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
                listar();
                desabilitarBotoes();
                desabilitarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao alterar fornecedor", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void excluirFornecedor() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId((Integer) jTableFornecedores.getValueAt(jTableFornecedores.getSelectedRow(), 0));

        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        if (fornecedorDAO.excluirFornecedor(fornecedor)) {
            JOptionPane.showMessageDialog(this, "Fornecedor excluido com sucesso!!!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
            listar();
            limparCampos();
            desabilitarBotoes();           
            desabilitarCampos();            
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir fornecedor", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void mostrar() {
        int index = jTableFornecedores.getSelectedRow();
        if (jTableFornecedores.getSelectedRow() != -1) {
            jTextNome.setText(lista.get(index).getNome());
            jTextEndereco.setText(lista.get(index).getEndereco());
            jTextBairro.setText(lista.get(index).getBairro());
            jTextCidade.setText(lista.get(index).getCidade());
            cbUf.setSelectedItem(lista.get(index).getUf());
            jTextCep.setText(lista.get(index).getCep());
            jTextTelefone.setText(lista.get(index).getTelefone());
            jTextEmail.setText(lista.get(index).getEmail());
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
        jTextFieldFiltro = new javax.swing.JTextField();
        jButtonPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFornecedores = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextEndereco = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextBairro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextCidade = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbUf = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextTelefone = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextEmail = new javax.swing.JTextField();
        jTextCep = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        btnSelecionarFornecedor = new javax.swing.JButton();
        jButtonNovo = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cliente");
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
        jPanel1.setForeground(new java.awt.Color(0, 102, 255));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Fornecedor");
        jPanel1.add(jLabel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Filtro por nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jTextFieldFiltro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jTextFieldFiltro, gridBagConstraints);

        jButtonPesquisar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/search.png"))); // NOI18N
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jButtonPesquisar, gridBagConstraints);

        jTableFornecedores.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTableFornecedores.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableFornecedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFornecedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableFornecedores);
        if (jTableFornecedores.getColumnModel().getColumnCount() > 0) {
            jTableFornecedores.getColumnModel().getColumn(0).setMinWidth(1);
            jTableFornecedores.getColumnModel().getColumn(0).setMaxWidth(200);
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
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
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

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Endereço");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel4, gridBagConstraints);

        jTextEndereco.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextEndereco.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextEndereco, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Bairro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel5, gridBagConstraints);

        jTextBairro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextBairro.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextBairro, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Cidade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel6, gridBagConstraints);

        jTextCidade.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextCidade.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextCidade, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("UF");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel7, gridBagConstraints);

        cbUf.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbUf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        cbUf.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(cbUf, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("CEP");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Telefone");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel9, gridBagConstraints);

        try {
            jTextTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) # ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextTelefone.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextTelefone, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jLabel10, gridBagConstraints);

        jTextEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextEmail.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextEmail, gridBagConstraints);

        try {
            jTextCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextCep.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel3.add(jTextCep, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        btnSelecionarFornecedor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSelecionarFornecedor.setText("Selecionar Fornecedor");
        btnSelecionarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarFornecedorActionPerformed(evt);
            }
        });
        jPanel4.add(btnSelecionarFornecedor);

        jButtonNovo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonNovo.setText("Novo");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonNovo);

        jButtonAlterar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonAlterar);

        jButtonExcluir.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonExcluir);

        jButtonSalvar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonSalvar.setText("Salvar");
        jButtonSalvar.setEnabled(false);
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonSalvar);

        jButtonCancelar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
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
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jPanel4, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        listar();
    }//GEN-LAST:event_formInternalFrameOpened

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        String nome = "%" + jTextFieldFiltro.getText() + "%";
        FornecedorDAO fornecedorDao = new FornecedorDAO();
        lista = fornecedorDao.pesquisaFornecedor(nome);
        DefaultTableModel dados = (DefaultTableModel) jTableFornecedores.getModel();
        dados.setRowCount(0);
        for (Fornecedor fornecedor : lista) {
            dados.addRow(new Object[]{
                fornecedor.getId(),
                fornecedor.getNome(),});
        }
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        desabilitarCampos();
        desabilitarBotoes();
        limparCampos();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        if (modo == Util.Constantes.INSERT_MODE) {
            incluiFornecedor();
        } else if (modo == Util.Constantes.EDIT_MODE) {
            alteraFornecedor();
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        if (jTableFornecedores.getSelectedRow() != -1) {
            int op = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja excluir o fornecedor " + lista.get(jTableFornecedores.getSelectedRow()).getNome(), "Confirmar exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (op == 0) {
                excluirFornecedor();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um cliente na tabela", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        if (jTableFornecedores.getSelectedRow() != -1) {
            limparCampos();
            mostrar();
            habilitarCampos();
            habilitarBotoes();
            modo = Util.Constantes.EDIT_MODE;
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um fornecedor na tabela", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        habilitarCampos();
        habilitarBotoes();
        limparCampos();
        modo = Util.Constantes.INSERT_MODE;
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void btnSelecionarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarFornecedorActionPerformed
        if (jTableFornecedores.getSelectedRow() != -1) {
            produtoFrame.setFornecedor(lista.get(jTableFornecedores.getSelectedRow()));
            this.dispose();
            produtoFrame.toFront();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um fornecedor na tabela", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSelecionarFornecedorActionPerformed

    private void jTableFornecedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFornecedoresMouseClicked
        mostrar();
    }//GEN-LAST:event_jTableFornecedoresMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelecionarFornecedor;
    private javax.swing.JComboBox<String> cbUf;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFornecedores;
    private javax.swing.JTextField jTextBairro;
    private javax.swing.JFormattedTextField jTextCep;
    private javax.swing.JTextField jTextCidade;
    private javax.swing.JTextField jTextEmail;
    private javax.swing.JTextField jTextEndereco;
    private javax.swing.JTextField jTextFieldFiltro;
    private javax.swing.JTextField jTextNome;
    private javax.swing.JFormattedTextField jTextTelefone;
    // End of variables declaration//GEN-END:variables
}

package View;

import Util.Constantes;
import com.sun.source.util.TaskEvent;
import java.awt.Color;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class TelaPrincipal extends javax.swing.JFrame {

    private String perfil = "USER";
    public TelaPrincipal() {
        initComponents();
        if(perfil.equals("ADM")){
            jMenuUsuario.setVisible(true);
        }else{
            jMenuUsuario.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jDesktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuSistema = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuCadastro = new javax.swing.JMenu();
        jMenuItemCliente = new javax.swing.JMenuItem();
        jMenuItemFornecedor = new javax.swing.JMenuItem();
        jMenuItemProduto = new javax.swing.JMenuItem();
        jMenuUsuario = new javax.swing.JMenuItem();
        jMenuVendas = new javax.swing.JMenu();
        jMenuItemRegistrarVendas = new javax.swing.JMenuItem();
        jMenuItemConsultarVendas = new javax.swing.JMenuItem();
        jMenuAjuda = new javax.swing.JMenu();
        jMenuItemSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TostaShop");

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jDesktopPaneLayout.setVerticalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane)
        );

        jMenuSistema.setMnemonic('s');
        jMenuSistema.setText("Sistema");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/sair.png"))); // NOI18N
        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuSistema.add(jMenuItem1);

        jMenuItem2.setText("Alterar cor fundo cliente");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuSistema.add(jMenuItem2);

        jMenuBar1.add(jMenuSistema);

        jMenuCadastro.setMnemonic('c');
        jMenuCadastro.setText("Cadastro");

        jMenuItemCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/user.png"))); // NOI18N
        jMenuItemCliente.setMnemonic('l');
        jMenuItemCliente.setText("Cliente");
        jMenuItemCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClienteActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemCliente);

        jMenuItemFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/supplier.png"))); // NOI18N
        jMenuItemFornecedor.setMnemonic('f');
        jMenuItemFornecedor.setText("Fornecedor");
        jMenuItemFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFornecedorActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemFornecedor);

        jMenuItemProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/product.png"))); // NOI18N
        jMenuItemProduto.setMnemonic('p');
        jMenuItemProduto.setText("Produto");
        jMenuItemProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProdutoActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuItemProduto);

        jMenuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/users.png"))); // NOI18N
        jMenuUsuario.setText("Usuário");
        jMenuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuUsuarioActionPerformed(evt);
            }
        });
        jMenuCadastro.add(jMenuUsuario);

        jMenuBar1.add(jMenuCadastro);

        jMenuVendas.setMnemonic('v');
        jMenuVendas.setText("Vendas");

        jMenuItemRegistrarVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/sale.png"))); // NOI18N
        jMenuItemRegistrarVendas.setMnemonic('r');
        jMenuItemRegistrarVendas.setText("Registrar Vendas");
        jMenuItemRegistrarVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRegistrarVendasActionPerformed(evt);
            }
        });
        jMenuVendas.add(jMenuItemRegistrarVendas);

        jMenuItemConsultarVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/sales.png"))); // NOI18N
        jMenuItemConsultarVendas.setMnemonic('c');
        jMenuItemConsultarVendas.setText("Consultar Vendas");
        jMenuItemConsultarVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConsultarVendasActionPerformed(evt);
            }
        });
        jMenuVendas.add(jMenuItemConsultarVendas);

        jMenuBar1.add(jMenuVendas);

        jMenuAjuda.setMnemonic('a');
        jMenuAjuda.setText("Ajuda");

        jMenuItemSobre.setMnemonic('s');
        jMenuItemSobre.setText("Sobre");
        jMenuItemSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSobreActionPerformed(evt);
            }
        });
        jMenuAjuda.add(jMenuItemSobre);

        jMenuBar1.add(jMenuAjuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItemSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSobreActionPerformed
        FrmSobre tl = new FrmSobre();
        tl.setVisible(true);
        jDesktopPane.add(tl);
    }//GEN-LAST:event_jMenuItemSobreActionPerformed

    private void jMenuItemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClienteActionPerformed
        ClienteFrame cf = new ClienteFrame();
        cf.setVisible(true);
        jDesktopPane.add(cf);
    }//GEN-LAST:event_jMenuItemClienteActionPerformed

    private void jMenuItemFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFornecedorActionPerformed
        FornecedorFrame ff = new FornecedorFrame();
        ff.setVisible(true);
        jDesktopPane.add(ff);
    }//GEN-LAST:event_jMenuItemFornecedorActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Color CorPadrao = (new java.awt.Color(Util.Constantes.COR_FUNDO));
        Color cor = JColorChooser.showDialog(this, "Escolha uma cor para a cor de fundo da tela cliente", CorPadrao);
        if (cor != null) {
            Util.Constantes.COR_FUNDO = cor.getRGB();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItemProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProdutoActionPerformed
        ProdutoFrame pf = new ProdutoFrame();
        pf.setVisible(true);
        jDesktopPane.add(pf);
        pf.requestFocus();
    }//GEN-LAST:event_jMenuItemProdutoActionPerformed

    private void jMenuItemRegistrarVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRegistrarVendasActionPerformed
        RegistrarVendaFrame rv = new RegistrarVendaFrame();
        rv.setVisible(true);
        jDesktopPane.add(rv);
    }//GEN-LAST:event_jMenuItemRegistrarVendasActionPerformed

    private void jMenuItemConsultarVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConsultarVendasActionPerformed
        ConsultarVendaFrame cv = new ConsultarVendaFrame();
        cv.setVisible(true);
        jDesktopPane.add(cv);        
    }//GEN-LAST:event_jMenuItemConsultarVendasActionPerformed

    private void jMenuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuUsuarioActionPerformed
        UsuarioFrame uf = new UsuarioFrame();
        uf.setVisible(true);
        jDesktopPane.add(uf);
    }//GEN-LAST:event_jMenuUsuarioActionPerformed

    
    public static void main(String args[]) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JMenu jMenuAjuda;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCadastro;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItemCliente;
    private javax.swing.JMenuItem jMenuItemConsultarVendas;
    private javax.swing.JMenuItem jMenuItemFornecedor;
    private javax.swing.JMenuItem jMenuItemProduto;
    private javax.swing.JMenuItem jMenuItemRegistrarVendas;
    private javax.swing.JMenuItem jMenuItemSobre;
    private javax.swing.JMenu jMenuSistema;
    private javax.swing.JMenuItem jMenuUsuario;
    private javax.swing.JMenu jMenuVendas;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

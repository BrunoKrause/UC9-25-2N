import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class ConsultaAluno extends javax.swing.JFrame {

    private void ativarCampos() {
        campoNome.setEnabled(true);
        campoTurma.setEnabled(true);
        campoEmail.setEnabled(true);
        
        btnEditar.setEnabled(true);
        btnExcluir.setEnabled(true);
    }
    
    private void desativarCampos() {
        campoNome.setEnabled(false);
        campoTurma.setEnabled(false);
        campoEmail.setEnabled(false);
        
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }
    
    private void limpar(){
        campoId.setText("");
        campoNome.setText("");
        campoTurma.setText("");
        campoEmail.setText("");
        
        campoId.requestFocus();
        
        desativarCampos();
    }
    
    private void carregarTabela(){
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Turma");
        modelo.addColumn("Email");
        
        Aluno aluno = new Aluno();
        
        ArrayList<Aluno> lista = aluno.listar();
        
        for (Aluno item: lista){
            modelo.addRow(new Object[]{
            item.getId(),
            item.getNome(),
            item.getTurma(),
            item.getEmail()
            });
        }
        
        tabelaAlunos.setModel(modelo);
    }
    
    public ConsultaAluno() {
        initComponents();
        carregarTabela();
        desativarCampos();
        setResizable(false);
        setTitle("Consulta de Alunos");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        campoId = new javax.swing.JTextField();
        campoNome = new javax.swing.JTextField();
        campoTurma = new javax.swing.JTextField();
        campoEmail = new javax.swing.JTextField();
        btnLocalizar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaAlunos = new javax.swing.JTable();
        btnAtualizar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        campoId.setBackground(new java.awt.Color(242, 242, 242));
        campoId.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "ID"));

        campoNome.setBackground(new java.awt.Color(242, 242, 242));
        campoNome.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Nome"));

        campoTurma.setBackground(new java.awt.Color(242, 242, 242));
        campoTurma.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Turma"));

        campoEmail.setBackground(new java.awt.Color(242, 242, 242));
        campoEmail.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Email"));

        btnLocalizar.setText("Localizar");
        btnLocalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizarActionPerformed(evt);
            }
        });

        btnEditar.setText("📝Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setText("🗑️Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        tabelaAlunos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelaAlunos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaAlunosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaAlunos);

        btnAtualizar.setText("🔄️Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(campoId, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLocalizar))
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAtualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLimpar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFechar))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLocalizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir)
                    .addComponent(btnLimpar)
                    .addComponent(btnFechar)
                    .addComponent(btnAtualizar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (campoId.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Digite o ID do Aluno");
            return;
        }
        
        int resposta = JOptionPane.showConfirmDialog(this, "Você realmente deseja excluir este aluno?", "Confirmação", JOptionPane.YES_NO_OPTION);
        
        if (resposta == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(campoId.getText().trim());
            
            Aluno aluno = new Aluno();
            boolean excluido = aluno.excluir(id);
            
            if (excluido){
                JOptionPane.showMessageDialog(this, "Aluno excluído com sucesso!", "Exclusão de aluno", JOptionPane.INFORMATION_MESSAGE);
                limpar();
                carregarTabela();
                return;
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível excluir nenhum aluno.", "Exclusão de aluno", JOptionPane.INFORMATION_MESSAGE);
                limpar();
                return;
            }
        }
        limpar();
        return;
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarActionPerformed
        if (campoId.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Digite o ID do Aluno");
            return;
        }
        
        try {
            int id = Integer.parseInt(campoId.getText().trim());
            Aluno aluno = new Aluno();
            Aluno encontrado = aluno.localizar(id);
            
            if (encontrado != null) {
                campoNome.setText(encontrado.getNome());
                campoTurma.setText(encontrado.getTurma());
                campoEmail.setText(encontrado.getEmail());
                ativarCampos();
                JOptionPane.showMessageDialog(this, "Aluno encontrado com sucesso!");
            } else {
                limpar();
            }
        } catch (NumberFormatException erro) {
           System.out.println("Ocorreu um erro" + erro.getMessage());
        }
    }//GEN-LAST:event_btnLocalizarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limpar();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        carregarTabela();
        limpar();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
        
        if (resposta == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (campoId.getText().trim().isEmpty() | campoNome.getText().trim().isEmpty() | campoTurma.getText().trim().isEmpty() || campoEmail.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }
        
        int resposta = JOptionPane.showConfirmDialog(this, "Você realmente deseja editar este(a) professor(a)?", "Confirmação de Edição", JOptionPane.YES_NO_OPTION);
        
        if (resposta == JOptionPane.YES_OPTION) {
            Aluno aluno = new Aluno();
            
            int id = Integer.parseInt(campoId.getText().trim());
            String nome = campoNome.getText().trim();
            String turma = campoTurma.getText().trim();
            String email = campoEmail.getText().trim();
            
            boolean editado = aluno.editar(id, nome, turma, email);
            
            if(editado) {
                    JOptionPane.showMessageDialog(this, "Aluno Editado com sucesso", "Informação", JOptionPane.INFORMATION_MESSAGE);
                    carregarTabela();
                    limpar();
                } else {
                    JOptionPane.showMessageDialog(this, "Não foi possível editar o aluno.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void tabelaAlunosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaAlunosMouseClicked
        int linha = tabelaAlunos.getSelectedRow();
        
        if (linha >= 0) {
            campoId.setText(tabelaAlunos.getValueAt(linha, 0).toString());
            campoNome.setText(tabelaAlunos.getValueAt(linha,1).toString());
            campoTurma.setText(tabelaAlunos.getValueAt(linha,2).toString());
            campoEmail.setText(tabelaAlunos.getValueAt(linha,3).toString());
            
            ativarCampos();
        }
    }//GEN-LAST:event_tabelaAlunosMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandbtnExcluir.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConsultaAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaAluno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnLocalizar;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoId;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoTurma;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tabelaAlunos;
    // End of variables declaration//GEN-END:variables
}

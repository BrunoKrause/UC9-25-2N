
package view;
import dao.ProdutoDAO;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Produto;
import util.SessaoUsuario;


public class FrmProduto extends javax.swing.JInternalFrame {

    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private long idProdutoSelecionado = 0;
    private final DecimalFormat formatoValor = new DecimalFormat("#,##00.00");
    private final DecimalFormat formatoQuantidade = new DecimalFormat("#,##0.000");
   
    public FrmProduto() {
        initComponents();
        configurarTela();
        listarProdutos();
        limparCampos();
        setTitle("Cadastro de Produtos");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
    }
    
    private void configurarTela() {

    txtCodigo.setEditable(false);

    btnExcluir.setEnabled(
            SessaoUsuario.isMaster()
    );

    tabelaProdutos.setSelectionMode(
            javax.swing.ListSelectionModel
                    .SINGLE_SELECTION
    );

    tabelaProdutos.setAutoCreateRowSorter(
            true
    );

    tabelaProdutos.setModel(
            criarModeloTabela()
    );
}
    
    private DefaultTableModel criarModeloTabela() {

    return new DefaultTableModel(
            new Object[]{
                "Código",
                "Descrição",
                "Unidade",
                "Preço de Custo",
                "Preço de Venda",
                "Estoque",
                "Estoque Mínimo",
                "Ativo"
            },
            0
    ) {

        @Override
        public boolean isCellEditable(
                int row,
                int column
        ) {

            return false;
        }
    };
}
    
    private void preencherTabela(
        List<Produto> produtos
) {

    DefaultTableModel modelo =
            (DefaultTableModel)
            tabelaProdutos.getModel();

    modelo.setRowCount(0);

    for (
            Produto produto :
            produtos
    ) {

        modelo.addRow(
                new Object[]{
                    produto.getIdProduto(),
                    produto.getDescricao(),
                    produto.getUnidade(),
                    formatoValor.format(
                            produto.getPrecoCusto()
                    ),
                    formatoValor.format(
                            produto.getPrecoVenda()
                    ),
                    formatoQuantidade.format(
                            produto.getEstoque()
                    ),
                    formatoQuantidade.format(
                            produto.getEstoqueMinimo()
                    ),
                    produto.isAtivo()
                            ? "Sim"
                            : "Não"
                }
        );
    }
}
    
    private void listarProdutos() {

    try {

        preencherTabela(
                produtoDAO.listarTodos()
        );

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar produtos.\n"
                + erro.getMessage()
        );
    }
}
    private void limparCampos() {

    idProdutoSelecionado = 0;

    txtCodigo.setText("");

    txtDescricao.setText("");

    cmbUnidade.setSelectedIndex(0);

    txtPrecoCusto.setText("0,00");

    txtPrecoVenda.setText("0,00");

    txtEstoque.setText("0,000");

    txtEstoqueMinimo.setText("0,000");

    chkAtivo.setSelected(true);

    txtDescricao.requestFocus();
}
    
    private BigDecimal converterDecimal(
        String texto
) {

    String valor =
            texto
            .trim()
            .replace(".", "")
            .replace(",", ".");

    return new BigDecimal(
            valor
    );
}
    
    private boolean validarCampos() {

    if (
            txtDescricao
            .getText()
            .trim()
            .isEmpty()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Informe a descrição do produto."
        );

        txtDescricao.requestFocus();

        return false;
    }

    if (
            cmbUnidade.getSelectedItem() == null
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Selecione a unidade."
        );

        cmbUnidade.requestFocus();

        return false;
    }

    try {

        BigDecimal precoCusto =
                converterDecimal(
                        txtPrecoCusto.getText()
                );

        BigDecimal precoVenda =
                converterDecimal(
                        txtPrecoVenda.getText()
                );

        BigDecimal estoque =
                converterDecimal(
                        txtEstoque.getText()
                );

        BigDecimal estoqueMinimo =
                converterDecimal(
                        txtEstoqueMinimo.getText()
                );

        if (
                precoCusto.compareTo(
                        BigDecimal.ZERO
                ) < 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "O preço de custo não pode ser negativo."
            );

            return false;
        }

        if (
                precoVenda.compareTo(
                        BigDecimal.ZERO
                ) < 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "O preço de venda não pode ser negativo."
            );

            return false;
        }

        if (
                estoque.compareTo(
                        BigDecimal.ZERO
                ) < 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "O estoque não pode ser negativo."
            );

            return false;
        }

        if (
                estoqueMinimo.compareTo(
                        BigDecimal.ZERO
                ) < 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "O estoque mínimo não pode ser negativo."
            );

            return false;
        }

    } catch (
            NumberFormatException erro
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Informe valores numéricos válidos."
        );

        return false;
    }

    return true;
}
    
    private Produto criarProdutoComCampos() {

    Produto produto =
            new Produto();

    produto.setIdProduto(
            idProdutoSelecionado
    );

    produto.setDescricao(
            txtDescricao
            .getText()
            .trim()
    );

    produto.setUnidade(
            cmbUnidade
            .getSelectedItem()
            .toString()
    );

    produto.setPrecoCusto(
            converterDecimal(
                    txtPrecoCusto.getText()
            )
    );

    produto.setPrecoVenda(
            converterDecimal(
                    txtPrecoVenda.getText()
            )
    );

    produto.setEstoque(
            converterDecimal(
                    txtEstoque.getText()
            )
    );

    produto.setEstoqueMinimo(
            converterDecimal(
                    txtEstoqueMinimo.getText()
            )
    );

    produto.setAtivo(
            chkAtivo.isSelected()
    );

    return produto;
}
    
    private void carregarProdutoSelecionado() {

    int linha =
            tabelaProdutos
            .getSelectedRow();

    if (linha == -1) {

        JOptionPane.showMessageDialog(
                this,
                "Selecione um produto na tabela."
        );

        return;
    }

    int linhaModelo =
            tabelaProdutos
            .convertRowIndexToModel(
                    linha
            );

    long idProduto =
            Long.parseLong(
                    tabelaProdutos
                    .getModel()
                    .getValueAt(
                            linhaModelo,
                            0
                    )
                    .toString()
            );

    try {

        Produto produto =
                produtoDAO.buscarPorId(
                        idProduto
                );

        if (
                produto == null
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Produto não encontrado."
            );

            return;
        }

        preencherCampos(
                produto
        );

        tabProduto.setSelectedIndex(
                0
        );

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar produto.\n"
                + erro.getMessage()
        );
    }
}
    
    private void preencherCampos(
        Produto produto
) {

    idProdutoSelecionado =
            produto.getIdProduto();

    txtCodigo.setText(
            String.valueOf(
                    produto.getIdProduto()
            )
    );

    txtDescricao.setText(
            produto.getDescricao()
    );

    cmbUnidade.setSelectedItem(
            produto.getUnidade()
    );

    txtPrecoCusto.setText(
            produto
            .getPrecoCusto()
            .toPlainString()
            .replace(".", ",")
    );

    txtPrecoVenda.setText(
            produto
            .getPrecoVenda()
            .toPlainString()
            .replace(".", ",")
    );

    txtEstoque.setText(
            produto
            .getEstoque()
            .toPlainString()
            .replace(".", ",")
    );

    txtEstoqueMinimo.setText(
            produto
            .getEstoqueMinimo()
            .toPlainString()
            .replace(".", ",")
    );

    chkAtivo.setSelected(
            produto.isAtivo()
    );
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabProduto = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtCodigo = new javax.swing.JTextField();
        txtDescricao = new javax.swing.JTextField();
        cmbUnidade = new javax.swing.JComboBox<>();
        txtPrecoCusto = new javax.swing.JTextField();
        txtPrecoVenda = new javax.swing.JTextField();
        txtEstoque = new javax.swing.JTextField();
        txtEstoqueMinimo = new javax.swing.JTextField();
        chkAtivo = new javax.swing.JCheckBox();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutos = new javax.swing.JTable();
        cmbFiltro = new javax.swing.JComboBox<>();
        txtPesquisa = new javax.swing.JTextField();
        btnLocalizar = new javax.swing.JButton();
        btnListarTodos = new javax.swing.JButton();
        btnEstoqueBaixo = new javax.swing.JButton();
        btnCarregar = new javax.swing.JButton();

        txtCodigo.setBackground(new java.awt.Color(242, 242, 242));
        txtCodigo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Código"));

        txtDescricao.setBackground(new java.awt.Color(242, 242, 242));
        txtDescricao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Descrição"));

        cmbUnidade.setBackground(new java.awt.Color(242, 242, 242));
        cmbUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UN", "KG", "G", "L", "ML", "M", "CM", "CX", "PC", "PCT" }));

        txtPrecoCusto.setBackground(new java.awt.Color(242, 242, 242));
        txtPrecoCusto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Preço de Custo"));

        txtPrecoVenda.setBackground(new java.awt.Color(242, 242, 242));
        txtPrecoVenda.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Preço de Venda"));
        txtPrecoVenda.addActionListener(this::txtPrecoVendaActionPerformed);

        txtEstoque.setBackground(new java.awt.Color(242, 242, 242));
        txtEstoque.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Estoque"));
        txtEstoque.addActionListener(this::txtEstoqueActionPerformed);

        txtEstoqueMinimo.setBackground(new java.awt.Color(242, 242, 242));
        txtEstoqueMinimo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Estoque Mínimo"));
        txtEstoqueMinimo.addActionListener(this::txtEstoqueMinimoActionPerformed);

        chkAtivo.setText("Ativo");

        btnNovo.setBackground(new java.awt.Color(242, 242, 242));
        btnNovo.setText("Novo");
        btnNovo.addActionListener(this::btnNovoActionPerformed);

        btnSalvar.setBackground(new java.awt.Color(242, 242, 242));
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        btnAlterar.setBackground(new java.awt.Color(242, 242, 242));
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(this::btnAlterarActionPerformed);

        btnExcluir.setBackground(new java.awt.Color(242, 242, 242));
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);

        btnCancelar.setBackground(new java.awt.Color(242, 242, 242));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtEstoqueMinimo)
                                .addGap(18, 18, 18)
                                .addComponent(txtEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPrecoCusto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbUnidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(chkAtivo)
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(btnAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(0, 134, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtDescricao))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecoCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEstoqueMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkAtivo)
                    .addComponent(txtEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnSalvar)
                    .addComponent(btnAlterar)
                    .addComponent(btnExcluir)
                    .addComponent(btnCancelar))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        tabProduto.addTab("Cadastro", jPanel1);

        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaProdutos);

        cmbFiltro.setBackground(new java.awt.Color(242, 242, 242));
        cmbFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DESCRICAO", "ID", "UNIDADE" }));
        cmbFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Filtro"));

        txtPesquisa.setBackground(new java.awt.Color(242, 242, 242));
        txtPesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Pesquisa"));

        btnLocalizar.setBackground(new java.awt.Color(242, 242, 242));
        btnLocalizar.setText("Localizar");
        btnLocalizar.addActionListener(this::btnLocalizarActionPerformed);

        btnListarTodos.setBackground(new java.awt.Color(242, 242, 242));
        btnListarTodos.setText("Listar Todos");
        btnListarTodos.addActionListener(this::btnListarTodosActionPerformed);

        btnEstoqueBaixo.setBackground(new java.awt.Color(242, 242, 242));
        btnEstoqueBaixo.setText("Estoque Baixo");
        btnEstoqueBaixo.addActionListener(this::btnEstoqueBaixoActionPerformed);

        btnCarregar.setBackground(new java.awt.Color(242, 242, 242));
        btnCarregar.setText("Carregar Selecionado");
        btnCarregar.addActionListener(this::btnCarregarActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cmbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLocalizar))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnListarTodos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEstoqueBaixo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCarregar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLocalizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListarTodos)
                    .addComponent(btnEstoqueBaixo)
                    .addComponent(btnCarregar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabProduto.addTab("Consulta", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabProduto)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEstoqueMinimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstoqueMinimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstoqueMinimoActionPerformed

    private void txtEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEstoqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstoqueActionPerformed

    private void txtPrecoVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecoVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecoVendaActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limparCampos();
        tabProduto.setSelectedIndex(0);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if(idProdutoSelecionado != 0){
            JOptionPane.showMessageDialog(this, "Existe um produto carregado para edição. \n Utilize Alterar ou clique em Novo.");
            
            return;
        }
        
        if (!validarCampos()){
            return;
        }
        
        try {
            Produto produto = criarProdutoComCampos();
            long codigo = produtoDAO.cadastrar(produto);
            
            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso. \n Código: " + codigo);
            
            limparCampos();
            listarProdutos();
        } catch (Exception erro){
            JOptionPane.showMessageDialog(this, "Não foi possível cadastrar o produto. \n" + erro.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnLocalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizarActionPerformed
        String pesquisa = txtPesquisa.getText().trim();
        String filtro = cmbFiltro.getSelectedItem().toString();
        
        if(pesquisa.isEmpty()){
            listarProdutos();
            return;
        }
        
        if(filtro.equals("ID")){
            try {
                Long.parseLong(pesquisa);
            } catch (NumberFormatException erro){
                JOptionPane.showMessageDialog(this, "Para pesquisar por ID informe apenas números.");
            }
            txtPesquisa.requestFocus();
            return;
        }
        
        try{
            List<Produto> produtos = produtoDAO.pesquisar(filtro, pesquisa);
            
            preencherTabela(produtos);
            
            if(produtos.isEmpty()){
                JOptionPane.showMessageDialog(this, "Nenhum produto encontrado");
            }
        } catch (Exception erro){
            JOptionPane.showMessageDialog(this, "Erro na pesquisa.\n"+ erro.getMessage());
        }
    }//GEN-LAST:event_btnLocalizarActionPerformed

    private void btnListarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarTodosActionPerformed
        txtPesquisa.setText("");
        listarProdutos();
    }//GEN-LAST:event_btnListarTodosActionPerformed

    private void btnEstoqueBaixoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstoqueBaixoActionPerformed
        try {
            List<Produto> produtos = produtoDAO.listarEstoqueBaixo();
            
            preencherTabela(produtos);
            
            if(produtos.isEmpty()){
                JOptionPane.showMessageDialog(this, "Nenhum produto está com estoque baixo.");
            }
        } catch (Exception erro){
            JOptionPane.showMessageDialog(this, "Erro ao consultar estoque baixo.\n" + erro.getMessage());
        }
    }//GEN-LAST:event_btnEstoqueBaixoActionPerformed

    private void btnCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarregarActionPerformed
        carregarProdutoSelecionado();
    }//GEN-LAST:event_btnCarregarActionPerformed

    private void tabelaProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProdutosMouseClicked
        if (evt.getClickCount() == 2){
            carregarProdutoSelecionado();
        }
    }//GEN-LAST:event_tabelaProdutosMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if (
            idProdutoSelecionado == 0
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Localize e carregue um produto antes de alterar."
        );

        return;
    }

    if (!validarCampos()) {

        return;
    }

    int resposta =
            JOptionPane.showConfirmDialog(
                    this,
                    "Deseja salvar as alterações deste produto?",
                    "Alterar Produto",
                    JOptionPane.YES_NO_OPTION
            );

    if (
            resposta
            != JOptionPane.YES_OPTION
    ) {

        return;
    }

    try {

        Produto produto =
                criarProdutoComCampos();

        boolean alterado =
                produtoDAO.alterar(
                        produto
                );

        if (alterado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Produto alterado com sucesso."
            );

            limparCampos();

            listarProdutos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum registro foi alterado."
            );
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Não foi possível alterar o produto.\n"
                + erro.getMessage()
        );
    }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (
            !SessaoUsuario.isMaster()
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Você não possui permissão para excluir produtos."
        );

        return;
    }

    if (
            idProdutoSelecionado == 0
    ) {

        JOptionPane.showMessageDialog(
                this,
                "Localize e carregue um produto antes de excluir."
        );

        return;
    }

    int resposta =
            JOptionPane.showConfirmDialog(
                    this,
                    "Deseja realmente excluir este produto?",
                    "Excluir Produto",
                    JOptionPane.YES_NO_OPTION
            );

    if (
            resposta
            != JOptionPane.YES_OPTION
    ) {

        return;
    }

    try {

        boolean excluido =
                produtoDAO.excluir(
                        idProdutoSelecionado
                );

        if (excluido) {

            JOptionPane.showMessageDialog(
                    this,
                    "Produto excluído com sucesso."
            );

            limparCampos();

            listarProdutos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Produto não encontrado."
            );
        }

    } catch (Exception erro) {

        JOptionPane.showMessageDialog(
                this,
                "Não foi possível excluir o produto.\n"
                + "Ele pode possuir compras ou vendas vinculadas.\n"
                + erro.getMessage()
        );
    }
    }//GEN-LAST:event_btnExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCarregar;
    private javax.swing.JButton btnEstoqueBaixo;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnListarTodos;
    private javax.swing.JButton btnLocalizar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JCheckBox chkAtivo;
    private javax.swing.JComboBox<String> cmbFiltro;
    private javax.swing.JComboBox<String> cmbUnidade;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane tabProduto;
    private javax.swing.JTable tabelaProdutos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtEstoque;
    private javax.swing.JTextField txtEstoqueMinimo;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtPrecoCusto;
    private javax.swing.JTextField txtPrecoVenda;
    // End of variables declaration//GEN-END:variables
}

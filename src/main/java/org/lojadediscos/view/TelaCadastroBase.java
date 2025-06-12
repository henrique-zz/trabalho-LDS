package org.lojadediscos.view;

import org.lojadediscos.services.RelatorioDiscos;
import org.lojadediscos.util.I18n;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Classe base para as telas criadas do JavaSwing, contendo alguns elementos que terão em comum nas classes de tela de
 * cadastro de discos e de clientes.
 */

public abstract class TelaCadastroBase extends JFrame {

    // os botões são inicializados sem texto. o texto será definido via i18n.
    protected JButton btnSalvar = new JButton();
    protected JButton btnListar = new JButton();
    protected JButton btnVenda = new JButton();
    protected JButton btnExcluir = new JButton();
    protected JButton btnCadastroCliente = new JButton();
    protected JButton btnCadastroDiscos = new JButton();
    protected JButton btnRelatorioEstoque = new JButton();
    protected JButton btnEditar = new JButton();

    public TelaCadastroBase() {

        setSize(650, 300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnSalvar.setBounds(80, 220, 100, 30);
        btnListar.setBounds(200, 220, 120, 30);
        btnCadastroCliente.setBounds(380, 10, 200, 30);
        btnCadastroDiscos.setBounds(380, 10, 200, 30);
        btnRelatorioEstoque.setBounds(380, 60, 250, 30);
        btnExcluir.setBounds(380, 110, 100, 30);
        btnEditar.setBounds(380, 160, 100, 30);
        btnVenda.setBounds(380, 220, 200, 30);

        add(btnSalvar);
        add(btnListar);
        add(btnVenda);
        add(btnExcluir);
        add(btnCadastroCliente);
        add(btnCadastroDiscos);
        add(btnRelatorioEstoque);
        add(btnEditar);

        btnVenda.addActionListener(this::irParaTelaVenda);
        btnCadastroCliente.addActionListener(this::abrirCadastroCliente);
        btnCadastroDiscos.addActionListener(this::abrirCadastroDisco);
        btnRelatorioEstoque.addActionListener(this::geracaoPDF);

        updateTexts();
    }

    // método para centralizar a atualização dos textos dos botões
    private void updateTexts() {
        btnSalvar.setText(I18n.getString("button.save"));
        btnListar.setText(I18n.getString("button.list"));
        btnVenda.setText(I18n.getString("button.sell"));
        btnExcluir.setText(I18n.getString("button.delete"));
        btnEditar.setText(I18n.getString("button.edit"));
        btnCadastroCliente.setText(I18n.getString("button.goToClientRegistration"));
        btnCadastroDiscos.setText(I18n.getString("button.goToDiscRegistration"));
        btnRelatorioEstoque.setText(I18n.getString("button.generateStockReport"));
    }

    protected abstract void salvar(ActionEvent e);
    protected abstract void listar(ActionEvent e);
    protected abstract void excluir(ActionEvent e);
    protected abstract void editar(ActionEvent e);

    private void irParaTelaVenda(ActionEvent e) {
        new TelaVenda();
        dispose();
    }

    protected void abrirCadastroCliente(ActionEvent e) {
        new TelaCadastroCliente();
        dispose();
    }

    protected void abrirCadastroDisco(ActionEvent e) {
        new TelaCadastroDisco();
        dispose();
    }

    protected void geracaoPDF(ActionEvent e){
        new RelatorioDiscos().gerarPDF();
        JOptionPane.showMessageDialog(this, I18n.getString("message.success.reportGenerated"));
    }
}

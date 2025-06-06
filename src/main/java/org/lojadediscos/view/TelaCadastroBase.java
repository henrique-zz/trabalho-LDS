package org.lojadediscos.view;

import org.lojadediscos.services.RelatorioDiscos;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Classe base para as telas criadas do JavaSwing, contendo alguns elementos que terão em comum nas classes de tela de
 * cadastro de discos e de clientes.
 */

public abstract class TelaCadastroBase extends JFrame {

    protected JButton btnSalvar = new JButton("Salvar");
    protected JButton btnListar = new JButton("Listar Todos");
    protected JButton btnVenda = new JButton("Realizar Venda");
    protected JButton btnExcluir = new JButton("Excluir");
    protected JButton btnCadastroCliente = new JButton("Ir para Cadastro de Clientes");
    protected JButton btnCadastroDiscos = new JButton("Ir para Cadastro de Discos");
    protected JButton btnRelatorioEstoque = new JButton("Gerar Relatório Estoque dos Discos");
    protected JButton btnEditar = new JButton("Editar");


    public TelaCadastroBase(String titulo) {
        setTitle(titulo);
        setSize(600, 300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnSalvar.setBounds(80, 220, 100, 30);
        btnListar.setBounds(200, 220, 120, 30);
        btnVenda.setBounds(350, 220, 200, 30);
        btnExcluir.setBounds(380, 70, 100, 30);
        btnCadastroCliente.setBounds(380, 10, 200, 30);
        btnCadastroDiscos.setBounds(380, 10, 200, 30);
        btnRelatorioEstoque.setBounds(320, 120, 250, 30);
        btnEditar.setBounds(380, 170, 100, 30);

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
        JOptionPane.showMessageDialog(this, "Relatório gerado com sucesso!");
    }
}

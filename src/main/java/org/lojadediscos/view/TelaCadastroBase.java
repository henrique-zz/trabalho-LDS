package org.lojadediscos.view;

import javax.swing.*;
import java.awt.event.ActionEvent;

public abstract class TelaCadastroBase extends JFrame {

    protected JButton btnSalvar = new JButton("Salvar");
    protected JButton btnListar = new JButton("Listar Todos");
    protected JButton btnVenda = new JButton("Realizar Venda");
    protected JButton btnExcluir = new JButton("Excluir");
    protected JButton btnCadastroCliente = new JButton("Ir para Cadastro de Clientes");
    protected JButton btnCadastroDiscos = new JButton("Ir para Cadastro de Discos");

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

        add(btnSalvar);
        add(btnListar);
        add(btnVenda);
        add(btnExcluir);
        add(btnCadastroCliente);
        add(btnCadastroDiscos);

        btnVenda.addActionListener(this::irParaTelaVenda);
        btnCadastroCliente.addActionListener(this::abrirCadastroCliente);
        btnCadastroDiscos.addActionListener(this::abrirCadastroDisco);
    }

    protected abstract void salvar(ActionEvent e);
    protected abstract void listar(ActionEvent e);
    protected abstract void excluir(ActionEvent e);

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
}

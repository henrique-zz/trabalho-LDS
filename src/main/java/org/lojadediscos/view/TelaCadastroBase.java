package org.lojadediscos.view;

import javax.swing.*;
import java.awt.event.ActionEvent;

public abstract class TelaCadastroBase extends JFrame {

    protected JButton btnSalvar = new JButton("Salvar");
    protected JButton btnListar = new JButton("Listar Todos");
    protected JButton btnVenda = new JButton("Realizar Venda");

    public TelaCadastroBase(String titulo) {
        setTitle(titulo);
        setSize(600, 300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnSalvar.setBounds(80, 220, 100, 30);
        btnListar.setBounds(200, 220, 120, 30);
        btnVenda.setBounds(350, 220, 200, 30);

        add(btnSalvar);
        add(btnListar);
        add(btnVenda);

        btnVenda.addActionListener(this::irParaTelaVenda);
    }

    protected abstract void salvar(ActionEvent e);
    protected abstract void listar(ActionEvent e);

    private void irParaTelaVenda(ActionEvent e) {
        new TelaVenda();
        dispose();
    }
}

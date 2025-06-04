package org.lojadediscos.view;

import org.lojadediscos.dao.ClienteDAO;
import org.lojadediscos.model.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TelaCadastroCliente extends TelaCadastroBase {

    private JTextField txtNome = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JTextField txtTelefone = new JTextField(20);

    private JButton btnVoltar = new JButton("Ir para Cadastro de Discos");

    public TelaCadastroCliente() {
        super("Cadastro de Cliente");

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 80, 25);
        txtNome.setBounds(100, 20, 200, 25);

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setBounds(20, 60, 80, 25);
        txtEmail.setBounds(100, 60, 200, 25);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(20, 100, 80, 25);
        txtTelefone.setBounds(100, 100, 200, 25);

        btnVoltar.setBounds(380, 10, 200, 30);

        add(lblNome);
        add(txtNome);
        add(lblEmail);
        add(txtEmail);
        add(lblTelefone);
        add(txtTelefone);
        add(btnVoltar);

        btnSalvar.addActionListener(this::salvar);
        btnListar.addActionListener(this::listar);
        btnVoltar.addActionListener(e -> {
            new TelaCadastroDisco();
            dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    protected void salvar(ActionEvent e) {
        Cliente cliente = new Cliente(
                txtNome.getText(),
                txtEmail.getText(),
                txtTelefone.getText()
        );
        ClienteDAO dao = new ClienteDAO();
        dao.salvar(cliente);
        JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!");
    }

    @Override
    protected void listar(ActionEvent e) {
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> lista = dao.listarTodos();

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum cliente cadastrado.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Cliente cliente : lista) {
                sb.append("ID: ").append(cliente.getId())
                        .append(" | Nome: ").append(cliente.getNome())
                        .append(" | E-mail: ").append(cliente.getEmail())
                        .append(" | Telefone: ").append(cliente.getTelefone())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        }
    }
}

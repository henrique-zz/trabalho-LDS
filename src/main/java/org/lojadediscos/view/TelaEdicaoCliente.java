package org.lojadediscos.view;

import org.lojadediscos.dao.ClienteDAO;
import org.lojadediscos.model.Cliente;

import javax.swing.*;
import java.awt.*;

/**
 * Classe para a tela quando apertarmos o botão "Editar" na nossa tela de cadastro de cliente.
 */

public class TelaEdicaoCliente extends JDialog {

    private final JTextField txtNome;
    private final JTextField txtEmail;
    private final JTextField txtTelefone;

    public TelaEdicaoCliente(JFrame parent, Cliente cliente) {
        super(parent, "Editar Cliente", true);
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        txtNome = new JTextField(cliente.getNome());
        txtEmail = new JTextField(cliente.getEmail());
        txtTelefone = new JTextField(cliente.getTelefone());

        JPanel painelCampos = new JPanel(new GridLayout(4, 2, 5, 5));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelCampos.add(new JLabel("Nome:"));
        painelCampos.add(txtNome);
        painelCampos.add(new JLabel("E-mail:"));
        painelCampos.add(txtEmail);
        painelCampos.add(new JLabel("Telefone:"));
        painelCampos.add(txtTelefone);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            cliente.setNome(txtNome.getText());
            cliente.setEmail(txtEmail.getText());
            cliente.setTelefone(txtTelefone.getText());

            new ClienteDAO().atualizar(cliente);
            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
            dispose();
        });

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }
}

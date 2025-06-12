package org.lojadediscos.view;

import org.lojadediscos.dao.ClienteDAO;
import org.lojadediscos.model.Cliente;
import org.lojadediscos.util.I18n;

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
        // título traduzido
        super(parent, I18n.getString("window.title.editClient"), true);
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        txtNome = new JTextField(cliente.getNome());
        txtEmail = new JTextField(cliente.getEmail());
        txtTelefone = new JTextField(cliente.getTelefone());

        JPanel painelCampos = new JPanel(new GridLayout(4, 2, 5, 5));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // labels traduzidas
        painelCampos.add(new JLabel(I18n.getString("label.name")));
        painelCampos.add(txtNome);
        painelCampos.add(new JLabel(I18n.getString("label.email")));
        painelCampos.add(txtEmail);
        painelCampos.add(new JLabel(I18n.getString("label.phone")));
        painelCampos.add(txtTelefone);

        // botões traduzidos
        JButton btnSalvar = new JButton(I18n.getString("button.save"));
        btnSalvar.addActionListener(e -> {
            cliente.setNome(txtNome.getText());
            cliente.setEmail(txtEmail.getText());
            cliente.setTelefone(txtTelefone.getText());

            new ClienteDAO().atualizar(cliente);
            // mensagem traduzida
            JOptionPane.showMessageDialog(this, I18n.getString("message.success.clientUpdated"));
            dispose();
        });

        JButton btnCancelar = new JButton(I18n.getString("button.cancel"));
        btnCancelar.addActionListener(e -> dispose());

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }
}

package org.lojadediscos.view;

import org.lojadediscos.dao.ClienteDAO;
import org.lojadediscos.model.Cliente;
import org.lojadediscos.util.I18n;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Classe para o cadastro de clientes no nosso banco de dados, usando a TelaCadastroBase como pai, além de implementações
 * que diferem dos atributos prsentes nesta.
 */

public class TelaCadastroCliente extends TelaCadastroBase {

    private JTextField txtNome = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JTextField txtTelefone = new JTextField(20);

    public TelaCadastroCliente() {
        super(); // chama o construtor pai sem argumentos
        setTitle(I18n.getString("window.title.clientRegistration")); // define o título aqui

        // labels traduzidas
        JLabel lblNome = new JLabel(I18n.getString("label.name"));
        lblNome.setBounds(20, 20, 80, 25);
        txtNome.setBounds(100, 20, 200, 25);

        JLabel lblEmail = new JLabel(I18n.getString("label.email"));
        lblEmail.setBounds(20, 60, 80, 25);
        txtEmail.setBounds(100, 60, 200, 25);

        JLabel lblTelefone = new JLabel(I18n.getString("label.phone"));
        lblTelefone.setBounds(20, 100, 80, 25);
        txtTelefone.setBounds(100, 100, 200, 25);

        add(lblNome);
        add(txtNome);
        add(lblEmail);
        add(txtEmail);
        add(lblTelefone);
        add(txtTelefone);

        btnCadastroCliente.setVisible(false);
        btnSalvar.addActionListener(this::salvar);
        btnListar.addActionListener(this::listar);
        btnExcluir.addActionListener(this::excluir);
        btnEditar.addActionListener(this::editar);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    protected void salvar(ActionEvent e) {
        Cliente cliente = new Cliente(txtNome.getText(), txtEmail.getText(), txtTelefone.getText());
        new ClienteDAO().salvar(cliente);
        // mensagem traduzida
        JOptionPane.showMessageDialog(this, I18n.getString("message.success.clientSaved"));
    }

    @Override
    protected void listar(ActionEvent e) {
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> lista = dao.listarTodos();

        if (lista.isEmpty()) {
            // mensagem traduzida
            JOptionPane.showMessageDialog(this, I18n.getString("message.info.noClientsRegistered"));
        } else {
            StringBuilder sb = new StringBuilder();
            for (Cliente cliente : lista) {
                // labels da lista traduzidas
                sb.append("ID: ").append(cliente.getId())
                        .append(" | ").append(I18n.getString("label.name")).append(" ").append(cliente.getNome())
                        .append(" | ").append(I18n.getString("label.email")).append(" ").append(cliente.getEmail())
                        .append(" | ").append(I18n.getString("label.phone")).append(" ").append(cliente.getTelefone())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        }
    }

    @Override
    protected void excluir(ActionEvent e) {
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> clientes = dao.listarTodos();

        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, I18n.getString("message.info.noClientsRegistered"));
            return;
        }

        JComboBox<Cliente> cbClientes = new JComboBox<>(clientes.toArray(new Cliente[0]));
        int opcao = JOptionPane.showConfirmDialog(
                this,
                cbClientes,
                // título do diálogo traduzido
                I18n.getString("dialog.title.selectClientToDelete"),
                JOptionPane.OK_CANCEL_OPTION
        );

        if (opcao == JOptionPane.OK_OPTION) {
            Cliente selecionado = (Cliente) cbClientes.getSelectedItem();
            if (selecionado != null) {
                boolean sucesso = dao.excluir(selecionado.getId());
                if (sucesso) {
                    // mensagens traduzidas
                    JOptionPane.showMessageDialog(this, I18n.getString("message.success.clientDeleted"));
                } else {
                    JOptionPane.showMessageDialog(this, I18n.getString("message.error.deleteClient"));
                }
            }
        }
    }

    @Override
    protected void editar(ActionEvent e) {
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> clientes = dao.listarTodos();

        if (clientes.isEmpty()) {
            // mensagem traduzida
            JOptionPane.showMessageDialog(this, I18n.getString("message.info.noClientsRegistered"));
            return;
        }

        JComboBox<Cliente> cbClientes = new JComboBox<>(clientes.toArray(new Cliente[0]));
        int opcao = JOptionPane.showConfirmDialog(
                this,
                cbClientes,
                // título do diálogo traduzido
                I18n.getString("dialog.title.selectClientToEdit"),
                JOptionPane.OK_CANCEL_OPTION
        );

        if (opcao == JOptionPane.OK_OPTION) {
            Cliente clienteSelecionado = (Cliente) cbClientes.getSelectedItem();
            if (clienteSelecionado != null) {
                new TelaEdicaoCliente(this, clienteSelecionado).setVisible(true);
            }
        }
    }
}

package org.lojadediscos.view;

import org.lojadediscos.dao.ClienteDAO;
import org.lojadediscos.dao.DiscoDAO;
import org.lojadediscos.model.Cliente;
import org.lojadediscos.model.Disco;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TelaCadastroCliente extends TelaCadastroBase {

    private JTextField txtNome = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JTextField txtTelefone = new JTextField(20);

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

        add(lblNome);
        add(txtNome);
        add(lblEmail);
        add(txtEmail);
        add(lblTelefone);
        add(txtTelefone);

        btnCadastroCliente.setVisible(false); // não permitir o botão que volta para esta mesma tela aparecer
        btnSalvar.addActionListener(this::salvar);
        btnListar.addActionListener(this::listar);
        btnExcluir.addActionListener(this::excluir);

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

    @Override
    protected void excluir(ActionEvent e) {
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> clientes = dao.listarTodos();

        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum cliente cadastrado.");
            return;
        }

        JComboBox<Cliente> cbClientes = new JComboBox<>(clientes.toArray(new Cliente[0]));
        int opcao = JOptionPane.showConfirmDialog(
                this,
                cbClientes,
                "Selecione o cliente para excluir",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (opcao == JOptionPane.OK_OPTION) {
            Disco selecionado = (Disco) cbClientes.getSelectedItem();
            if (selecionado != null) {
                boolean sucesso = dao.excluir(selecionado.getId());
                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir o cliente.");
                }
            }
        }
    }
}
